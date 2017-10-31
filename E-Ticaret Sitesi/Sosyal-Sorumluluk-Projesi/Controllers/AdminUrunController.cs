using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models;
using System.Web.Helpers;
using System.IO;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{ 
    public class AdminUrunController : Controller
    {
        Model1 db = new Model1();

      
        // GET: AdminUrun
        public ActionResult Index()
        {
            var urunler = db.urunlers.ToList();
            return View(urunler);
        }

        // GET: AdminUrun/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: AdminUrun/Create
        public ActionResult Create()
        {

            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi");
            ViewBag.kullanici_id = new SelectList(db.kullanicilars, "kullanici_id", "ad_soyad");
            ViewBag.memleket_id = new SelectList(db.memlekets, "memleket_id", "memleket_adi");

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
                        var yenietiket = new etiket { etiket_adi = i };


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

            var urun = db.urunlers.Where(u => u.urun_id == id).SingleOrDefault();

            if (urun == null)
            {

                return HttpNotFound();
            }

            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi", urun.kategori_id);



            return View(urun);
        }

        // POST: AdminUrun/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, urunler urun,HttpPostedFileBase resim)
        {
            try
            {
                var urun1 = db.urunlers.Where(u => u.urun_id == id).SingleOrDefault();

                    if (resim!=null)
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
                    urun1.urun_adi = urun.urun_adi;
                    urun1.urun_icerik = urun.urun_icerik;
                    urun1.kategori_id = urun.kategori_id;
                    db.SaveChanges();


                }
                return RedirectToAction("Index");
            }

           


            catch
            {
                return View();
            }
        }

        // GET: AdminUrun/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: AdminUrun/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
