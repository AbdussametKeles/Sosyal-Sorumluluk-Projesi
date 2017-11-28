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
using PagedList;
using PagedList.Mvc;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{ 
    public class AdminUyeController : Controller
    {
        private Model1 db = new Model1();

        // GET: AdminUye
        public ActionResult Index(string sortOrder, string currentFilter, string searchString, int? page)
        {
            ViewBag.CurrentSort = sortOrder;
            ViewBag.NameSortParm = String.IsNullOrEmpty(sortOrder) ? "name_desc" : "";
          

            if (searchString != null)
            {
                page = 1;
            }
            else
            {
                searchString = currentFilter;
            }

            ViewBag.CurrentFilter = searchString;

            var uye = from s in db.kullanicilars
                           select s;
            if (!String.IsNullOrEmpty(searchString))
            {
                uye = uye.Where(s => s.yetki.yetkiAdi.Contains(searchString)
                                       || s.memleket.memleketAdi.Contains(searchString));
            }
            switch (sortOrder)
            {
                case "name_desc":
                    uye = uye.OrderByDescending(s => s.yetki.yetkiAdi);
                    break;
                  
                default: 
                    uye = uye.OrderBy(s => s.yetki.yetkiAdi);
                    break;
            }

        

            int pageSize = 3;
            int pageNumber = (page ?? 1); 
            return View(uye.ToPagedList(pageNumber, pageSize)); 
        }

        // GET: AdminUye/Details/5
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

        // GET: AdminUye/Create
        public ActionResult Create()
        {
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi");
            ViewBag.yetkiID = new SelectList(db.yetkis, "yetkiID", "yetkiAdi");
            return View();
        }

        // POST: AdminUye/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "kullaniciID,yetkiID,memleketID,adsoyad,kullaniciAdi,mail,sifre,telefon,resim")] kullanicilar kullanicilar,HttpPostedFileBase resim)
        {
            if (ModelState.IsValid)
            {


                if (resim != null)
                {

                    WebImage img = new WebImage(resim.InputStream);
                    FileInfo fotoinfo = new FileInfo(resim.FileName);

                    string newfoto = Guid.NewGuid().ToString() + fotoinfo.Extension;
                    img.Resize(150, 150);
                    img.Save("~/Uploads/resimler/" + newfoto);
                    kullanicilar.resim = "/Uploads/resimler/" + newfoto;
                    kullanicilar.yetkiID = 2;

                    //Session["kullaniciID"] = kullanicilar.kullaniciID;
                    //Session["yetkiID"] = kullanicilar.yetkiID;


                    db.kullanicilars.Add(kullanicilar);
                    db.SaveChanges();
                   

                }

                else
                {
                    ModelState.AddModelError("Foto", "Foto Seçiniz:");
                }




                db.kullanicilars.Add(kullanicilar);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi", kullanicilar.memleketID);
            ViewBag.yetkiID = new SelectList(db.yetkis, "yetkiID", "yetkiAdi", kullanicilar.yetkiID);
            return View(kullanicilar);
        }

        // GET: AdminUye/Edit/5
        public ActionResult Edit(int? id)
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
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi", kullanicilar.memleketID);
            ViewBag.yetkiID = new SelectList(db.yetkis, "yetkiID", "yetkiAdi", kullanicilar.yetkiID);
            return View(kullanicilar);
        }

        // POST: AdminUye/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "kullaniciID,yetkiID,memleketID,adsoyad,kullaniciAdi,mail,sifre,telefon,resim")] kullanicilar kullanicilar, HttpPostedFileBase resim, int id)
        {
            if (ModelState.IsValid)
            {
                var uye1 = db.kullanicilars.Where(k => k.kullaniciID == id).SingleOrDefault();

                if (resim != null)
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
                uye1.sifre = kullanicilar.sifre;

                uye1.telefon = kullanicilar.telefon;
                db.SaveChanges();
                Session["kullaniciadi"] = kullanicilar.kullaniciID;


                return RedirectToAction("Index", "AdminUye", new { id = uye1.kullaniciID });


                db.Entry(kullanicilar).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi", kullanicilar.memleketID);
            ViewBag.yetkiID = new SelectList(db.yetkis, "yetkiID", "yetkiAdi", kullanicilar.yetkiID);
            return View(kullanicilar);
        }

        //GET: AdminUye/Delete/5
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
            db.kullanicilars.Remove(kullanicilar);
            db.SaveChanges();
            return RedirectToAction("Index");
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
