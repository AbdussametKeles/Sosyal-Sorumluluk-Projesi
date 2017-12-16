using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models;
using System.Web.Helpers;
using System.IO;
using System.Net;
using PagedList;
using PagedList.Mvc;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class AdminUrunController : Controller
    {
        Model1 db = new Model1();


        // GET: AdminUrun
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

            var urun = from s in db.urunlers
                       select s;
            if (!String.IsNullOrEmpty(searchString))
            {
                urun = urun.Where(s => s.kategoriler.kategoriAdi.Contains(searchString)
                                       || s.memleket.memleketAdi.Contains(searchString));

            }
            switch (sortOrder)
            {
                case "name_desc":
                    urun = urun.OrderByDescending(s => s.kategoriler.kategoriAdi);
                    break;



                default:
                    urun = urun.OrderBy(s => s.kategoriler.kategoriAdi);
                    break;
            } 

            int pageSize = 3;
            int pageNumber = (page ?? 1);
            return View(urun.ToPagedList(pageNumber, pageSize));
        }

        // GET: AdminUrun/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            urunler urun = db.urunlers.Find(id);
            if (urun == null)
            {
                return HttpNotFound();
            }
            return View(urun);
        }

        // GET: AdminUrun/Create
        public ActionResult Create()
        {

            ViewBag.kategoriID = new SelectList(db.kategorilers, "kategoriID", "kategoriAdi");
            ViewBag.kullaniciID = new SelectList(db.kullanicilars, "kullaniciID", "adsoyad");
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi");

            return View();  
        }

        // POST: AdminUrun/Create
        [HttpPost]
        public ActionResult Create(urunler urun, HttpPostedFileBase resim)
        {
            ViewBag.kategoriID = new SelectList(db.kategorilers, "kategoriID", "kategoriAdi");
            ViewBag.kullaniciID = new SelectList(db.kullanicilars, "kullaniciID", "adsoyad");
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi"); 
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

                return RedirectToAction("Index");  



            }
            return View(urun);

        }

        // GET: AdminUrun/Edit/5
        public ActionResult Edit(int id)
        {

            var urun = db.urunlers.Where(u => u.urunID == id).SingleOrDefault();

            if (urun == null)
            {

                return HttpNotFound();
            }

            ViewBag.kategoriID = new SelectList(db.kategorilers, "kategoriID", "kategoriAdi", urun.kategoriID);



            return View(urun);
        }

        // POST: AdminUrun/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, urunler urun, HttpPostedFileBase resim)
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
                return RedirectToAction("Index");

              

            } 

          
           

              return View(urun);

        }

                 


  
        // GET: AdminUrun/Delete/5
        public ActionResult Delete(int id)
        {
            var urun1 = db.urunlers.Where(u => u.urunID == id).SingleOrDefault(); 
            ViewData["urunID"] = "";
            ViewData["kategoriID"] = "";
            ViewData["urunAdi"] = "";
            ViewData["urunIcerik"] = "";
            ViewData["memleketID"] = "";
            ViewData["tarih"] = "";
            ViewData["resim"] = "";
            ViewData["kullaniciID"] = "";
            ViewData["yorumID"] = "";



          

            if (urun1==null)
            {
                return HttpNotFound();
            }




            return View(urun1);
        }

        // POST: AdminUrun/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            var urun1 = db.urunlers.Where(u => u.urunID == id).SingleOrDefault();

            ViewData["urunID"] = "";
            ViewData["kategoriID"] = "";
            ViewData["urunAdi"] = "";
            ViewData["urunIcerik"] = "";
            ViewData["memleketID"] = "";
            ViewData["tarih"] = "";
            ViewData["resim"] = "";
            ViewData["kullaniciID"] = "";
            ViewData["yorumID"] = "";



            return RedirectToAction("/ArsivSil/urunID/");


            
        } 




    

         









}
}

