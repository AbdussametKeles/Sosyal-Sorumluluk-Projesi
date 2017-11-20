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
        public ActionResult Index(int Page=1)
        {
            var urunler = db.urunlers.OrderByDescending(u=>u.urunID).ToPagedList(Page, 10);
            return View(urunler);
        } 

        // GET: AdminUrun/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            urunler urun = db.urunlers.Find(id);
            if (urun== null)
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
        public ActionResult Create(urunler urun, string etiketler, HttpPostedFileBase resim)
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


                if (etiketler != null)
                {
                    String[] etiketdizi = etiketler.Split(',');
                    foreach (var i in etiketdizi)
                    {
                        var yenietiket = new etiket { etiketAdi = i };


                        db.etikets.Add(yenietiket);



                    }
                    db.urunlers.Add(urun);
                    db.SaveChanges();

                    return RedirectToAction("Index");
                }




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

            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategoriID", "kategoriAdi", urun.kategoriID);



            return View(urun);
        }

        // POST: AdminUrun/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, urunler urun, HttpPostedFileBase resim)
        {
            try
            {
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
                    db.SaveChanges();
                    return RedirectToAction("Index");

                }
                return View();
            }




            catch
            {
                ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi", urun.kategoriID);
                return View(urun);
            }
        }

        // GET: AdminUrun/Delete/5
        public ActionResult Delete(int id)
        {


            var urun = db.urunlers.Where(u => u.urunID == id).SingleOrDefault();

            if (urun==null)
            {
                return HttpNotFound();
            }




            return View(urun);
        }

        // POST: AdminUrun/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
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

                foreach (var i in urun1.etikets.ToList())
                {
                    db.etikets.Remove(i);
                }

                db.urunlers.Remove(urun1);
                db.SaveChanges();

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }

             
        }
    }
}

