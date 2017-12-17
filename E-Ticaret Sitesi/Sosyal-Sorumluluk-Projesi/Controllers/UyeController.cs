using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models;
using System.Web.Helpers;
using System.IO;
using System.Web.Security;
using PagedList;
using PagedList.Mvc;
using System.Drawing;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class UyeController : Controller
    {
        private Model1 db = new Model1();

        // GET: Uye
        public ActionResult Index(int id)
        {
            var uye = db.kullanicilars.Where(k => k.kullaniciID == id).SingleOrDefault();
           
            if (Convert.ToInt32(Session["kullaniciID"]) != uye.kullaniciID)
            {
                return HttpNotFound();
            }


            return View(uye);
         
        }

        // GET: Uye/Details/5 
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            kullanicilar kullanicilar = db.kullanicilars.Find(id);
            if (kullanicilar == null)
            {
                return HttpNotFound();
            }
            return View(kullanicilar);
        }

        public ActionResult Login()
        {
            return View();
        }
        [HttpPost]
        public ActionResult Login(kullanicilar uye,string sifre)
        {
            var md5pass = Crypto.Hash(sifre, "MD5");
            var login = db.kullanicilars.Where(k => k.mail == uye.mail).SingleOrDefault();

            try
            { 

                if (login.mail == uye.mail && login.sifre==md5pass || login.mail == uye.mail && login.sifre==uye.sifre)
                {   
                        
                    Session["kullaniciID"] = login.kullaniciID;
                    Session["mail"] = login.mail;
                    Session["yetkiID"] = login.yetkiID;
                    Session["sifre"] = login.sifre;

                    return RedirectToAction("Index", "Home");




                }

                else if (login.mail != uye.mail)
                {
                    Session["kullaniciID"] = login.kullaniciID;
                    Session["mail"] = login.mail;
                    Session["sifre"] = login.sifre;
                    Session["yetkiID"] = login.yetkiID;



                    ViewBag.Uyari = "Kullanıcı Login Bilgilerinizi Kontrol Ediniz";
                }


                else
                {
                    ViewBag.Uyari = "Kullanıcı Login Bilgilerinizi Kontrol Ediniz";
                }




            }
            catch (NullReferenceException)
            {

               

                ViewBag.Uyari = "Kullanıcı Login Bilgilerinizi Kontrol Ediniz";

                //return RedirectToAction("Login", "Uye");

            }
           

            return View(login);



        }
        public ActionResult Logout()
        {
            Session["kullaniciID"] = null;
            Session.Abandon();
            return RedirectToAction("Index", "Home");
        }
         
           public ActionResult SifremiUnuttum()
        {
            return View();
        }
        [HttpPost]
        public ActionResult SifremiUnuttum(kullanicilar uye1)
        {
            Random rnd = new Random();
             
            var sifrem = db.kullanicilars.Where(k => k.kullaniciAdi == uye1.kullaniciAdi).SingleOrDefault();


            

            string yeniSifrem = rnd.Next(100,1000000000).ToString();
            

          
          

           

            

           



            try
            {
                
                if (sifrem.kullaniciAdi==uye1.kullaniciAdi && uye1.gizliSoruCevap=="ankara")
                { 
                     
                    uye1.sifre = yeniSifrem;
                    sifrem.sifre = uye1.sifre;
                    db.SaveChanges();
                    ViewBag.Mesaj = "Geçici Şifreniz Budur lütfen bu şifreyi değiştiriniz:" + yeniSifrem; 
                     
                }  
                else 
                {
                    ViewBag.Mesaj = "Bilgilerinizi Kontrol Ediniz";

                   
                }
                 
            }
            catch (NullReferenceException)
            {
                ViewBag.Mesaj = "Bilgilerinizi Kontrol Ediniz";

            }
             
            return View();  



        } 




        // GET: Uye/Create
        public ActionResult Create()
        {
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi");


            
            return View();
        }

        // POST: Uye/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "kullaniciID,yetkiID,memleketID,adsoyad,kullaniciAdi,mail,sifre,telefon,resim,gizliSoru,gizliSoruCevap")] kullanicilar kullanicilar,string sifre,HttpPostedFileBase resim)
        {
            var md5pass = sifre; 
                if (ModelState.IsValid)
                {
                 

                    if (resim != null)  
                    {

                        WebImage img = new WebImage(resim.InputStream);
                        FileInfo fotoinfo = new FileInfo(resim.FileName);

                        string newfoto = Guid.NewGuid().ToString() + fotoinfo.Extension;
                        img.Resize(100, 100); 
                        img.Save("~/Uploads/resimler/" + newfoto);
                        kullanicilar.resim = "/Uploads/resimler/" + newfoto;
                        kullanicilar.yetkiID = 2;
                        kullanicilar.sifre = Crypto.Hash(md5pass, "MD5");

                    //Session["kullaniciID"] = kullanicilar.kullaniciID;
                    //Session["yetkiID"] = kullanicilar.yetkiID;


                        db.kullanicilars.Add(kullanicilar);
                        db.SaveChanges();
                    Session["kullaniciID"] = kullanicilar.kullaniciID;
                    Session["mail"] = kullanicilar.mail;
                         return RedirectToAction("Index", "Home");
                   
                  
                    }

                    else
                    {
                        ModelState.AddModelError("Foto", "Foto Seçiniz:");
                    }
                     
                } 
          
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi", kullanicilar.memleketID);
           
           
            return View(kullanicilar);
        }

        // GET: Uye/Edit/5
        public ActionResult Edit(int id)
        {
            var uye = db.kullanicilars.Where(k => k.kullaniciID == id).SingleOrDefault();

            if (Convert.ToInt32(Session["kullaniciID"])!=uye.kullaniciID)
            {
                return HttpNotFound();
            }


            return View(uye);

        } 

        // POST: Uye/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "kullaniciID,yetkiID,memleketID,adsoyad,kullaniciAdi,mail,sifre,telefon,resim")] kullanicilar kullanicilar,string sifre, HttpPostedFileBase resim, int id)
        {
            var md5pass = sifre;
            if (ModelState.IsValid)
            {
                var uye1 = db.kullanicilars.Where(k => k.kullaniciID == id).SingleOrDefault();

                if (resim!=null)
                {

                    if (System.IO.File.Exists(Server.MapPath(kullanicilar.resim)))
                    {
                        System.IO.File.Delete(Server.MapPath(uye1.resim));
                    }

                    WebImage img = new WebImage(resim.InputStream);

                    FileInfo resiminfo = new FileInfo(resim.FileName);

                    string newfoto = Guid.NewGuid().ToString() + resiminfo.Extension;
                    img.Resize(150, 150);
                    img.Save("~/Uploads/uyeler/" + newfoto);
                    uye1.resim = "/Uploads/uyeler/" + newfoto;
                     
                    }

                    uye1.adsoyad = kullanicilar.adsoyad;
                    uye1.kullaniciAdi = kullanicilar.kullaniciAdi;
                    uye1.mail = kullanicilar.mail;
                uye1.sifre = Crypto.Hash(md5pass, "MD5");
                  
                    uye1.telefon = kullanicilar.telefon;
                    db.SaveChanges();
                    Session["kullaniciadi"] = kullanicilar.kullaniciID;
              
                    return RedirectToAction("Index", "Home", new { id = uye1.kullaniciID });

                    


                




                db.Entry(kullanicilar).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi", kullanicilar.memleketID);
          
            return View(kullanicilar);
        }

        // GET: AdminUye/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            kullanicilar kullanicilar = db.kullanicilars.Find(id);
            if (kullanicilar == null)
            {
                return HttpNotFound();
            }
            return View(kullanicilar);
        }

        // POST: AdminUye/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {


            kullanicilar kullanicilar = db.kullanicilars.Find(id);

            Session["kullaniciID"] = kullanicilar.kullaniciID;

            db.kullanicilars.Remove(kullanicilar);
            db.SaveChanges();



            Session["kullaniciID"] = null;
            Session.Abandon();
            return RedirectToAction("Index", "Home");



        } 


        public ActionResult Urunlerim(int id,int Page=1)
        {
            var urunler = db.urunlers.OrderByDescending(u=>u.urunID).Where(u => u.kullanicilar.kullaniciID == id).ToPagedList(Page, 5);




            return View(urunler);
            
        }







        // GET: AdminUrun/Create
        public ActionResult UrunCreate()
        {

            ViewBag.kategoriID = new SelectList(db.kategorilers, "kategoriID", "kategoriAdi");
            ViewBag.kullaniciID = new SelectList(db.kullanicilars, "kullaniciID", "adsoyad");
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi");

            return View();
        }

        // POST: AdminUrun/Create
        [HttpPost]
        public ActionResult UrunCreate(urunler urun,  HttpPostedFileBase resim)
        {
             
            if (ModelState.IsValid)
            {

                if (resim != null)
                {
                    WebImage img = new WebImage(resim.InputStream);

                    FileInfo resiminfo = new FileInfo(resim.FileName);

                    string newfoto = Guid.NewGuid().ToString() + resiminfo.Extension;
                    img.Resize(800, 350);
                    img.Save("~/Uploads/urunler/" + newfoto);
                    urun.resim = "/Uploads/urunler/" + newfoto;

                }


                    db.urunlers.Add(urun);
                    db.SaveChanges();

                    return RedirectToAction("Index", "Home", "kullaniciID");
                }

                ViewBag.kategoriID = new SelectList(db.kategorilers, "kategoriID", "kategoriAdi");
                ViewBag.kullaniciID = new SelectList(db.kullanicilars, "kullaniciID", "adsoyad");
                ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi");

              return View(urun);

            }
          




        // GET: UyeUrun/Delete/5
        public ActionResult UrunDelete(int id)
        {


            var urun = db.urunlers.Where(u => u.urunID == id).SingleOrDefault();

            if (urun == null)
            {
                return HttpNotFound();
            }




            return View(urun);
        }

        // POST: UyeUrun/Delete/5
        [HttpPost]
        public ActionResult UrunDelete(int id, FormCollection collection)
        {
            try
            {

                var urun1 = db.urunlers.Where(u => u.urunID == id).SingleOrDefault();

                if (urun1 == null)
                {
                    return HttpNotFound();
                }

                if (System.IO.File.Exists(Server.MapPath(urun1.resim)))
                {
                    System.IO.File.Delete(Server.MapPath(urun1.resim));
                }

                foreach (var i in urun1.yorums.ToList())
                {
                    db.yorums.Remove(i);
                }

              

                db.urunlers.Remove(urun1);
                db.SaveChanges();
                 
                return RedirectToAction("Index", "Home", "kullaniciID");
            }
            catch
            {
                return View();
            }


        }

        // GET: UyeUrun/Edit/5
        public ActionResult UrunEdit(int id)
        { 

            var urun = db.urunlers.Where(u => u.urunID == id).SingleOrDefault();

            if (urun == null)
            {

                return HttpNotFound();
            }

            ViewBag.kategoriID = new SelectList(db.kategorilers, "kategoriID", "kategoriAdi", urun.kategoriID);



            return View(urun);
        }

        // POST: UyeUrun/Edit/5
        [HttpPost]
        public ActionResult UrunEdit(int id, urunler urun, HttpPostedFileBase resim)
        {

           ViewBag.kategoriID = new SelectList(db.kategorilers, "kategoriID", "kategoriAdi", urun.kategoriID);

            var urun1 = db.urunlers.Where(u => u.urunID == id).SingleOrDefault();

            if (resim != null)
            {
                if (System.IO.File.Exists(Server.MapPath(urun1.resim)))
                {
                    System.IO.File.Delete(Server.MapPath(urun1.resim));
                }

                WebImage img = new WebImage(resim.InputStream);

                FileInfo resiminfo = new FileInfo(resim.FileName);

                string newfoto = Guid.NewGuid().ToString() + resiminfo.Extension;
                img.Resize(800, 350);
                img.Save("~/Uploads/urunler/" + newfoto);
                urun1.resim = "/Uploads/urunler/" + newfoto;
                urun1.urunAdi = urun.urunAdi;
                urun1.urunİcerik = urun.urunİcerik;
                urun1.kategoriID = urun.kategoriID;
                urun1.tarih = urun.tarih;
                db.SaveChanges();
                return RedirectToAction( "Urunlerim/" + Session["kullaniciID"].ToString());



            }




            return View(urun);

        }


















        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
