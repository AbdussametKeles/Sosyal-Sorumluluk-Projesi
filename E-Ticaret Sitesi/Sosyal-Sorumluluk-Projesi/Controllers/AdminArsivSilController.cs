using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class AdminArsivSilController : Controller
    {
        private Model1 db = new Model1();

        // GET: AdminArsivSil
        public ActionResult Index()
        {
            var urunlers = db.urunlers.Include(u => u.kategoriler).Include(u => u.kullanicilar).Include(u => u.memleket);
            return View(urunlers.ToList());

            var ViewDataurun_id = ViewData["urun_id"];
            var ViewDatakategori_id = ViewData["kategori_id"];
            var ViewDataurun_adi = ViewData["urun_adi"];
            var ViewDataresim = ViewData["resim"];
            var ViewDataurun_icerik = ViewData["urun_icerik"];
            var ViewDatatarih = ViewData["tarih"];
            var ViewDatamemleket_id = ViewData["memleket_id"];
            var ViewDatakullanici_id = ViewData["kullanici_id"];


        }

        // GET: AdminArsivSil/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            urunler urunler = db.urunlers.Find(id);
            if (urunler == null)
            {
                return HttpNotFound();
            }
            return View(urunler);
        }

        // GET: AdminArsivSil/Create
        public ActionResult Create()
        {
            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi");
            ViewBag.kullanici_id = new SelectList(db.kullanicilars, "kullanici_id", "ad_soyad");
            ViewBag.memleket_id = new SelectList(db.memlekets, "memleket_id", "memleket_adi");
            return View();
        }

        // POST: AdminArsivSil/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "urun_id,kategori_id,urun_adi,resim,urun_icerik,tarih,memleket_id,kullanici_id")] urunler urunler)
        {
            if (ModelState.IsValid)
            {
                db.urunlers.Add(urunler);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi", urunler.kategori_id);
            ViewBag.kullanici_id = new SelectList(db.kullanicilars, "kullanici_id", "ad_soyad", urunler.kullanici_id);
            ViewBag.memleket_id = new SelectList(db.memlekets, "memleket_id", "memleket_adi", urunler.memleket_id);
            return View(urunler);
        }

        // GET: AdminArsivSil/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            urunler urunler = db.urunlers.Find(id);
            if (urunler == null)
            {
                return HttpNotFound();
            }
            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi", urunler.kategori_id);
            ViewBag.kullanici_id = new SelectList(db.kullanicilars, "kullanici_id", "ad_soyad", urunler.kullanici_id);
            ViewBag.memleket_id = new SelectList(db.memlekets, "memleket_id", "memleket_adi", urunler.memleket_id);
            return View(urunler);
        }

        // POST: AdminArsivSil/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "urun_id,kategori_id,urun_adi,resim,urun_icerik,tarih,memleket_id,kullanici_id")] urunler urunler)
        {
            if (ModelState.IsValid)
            {
                db.Entry(urunler).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi", urunler.kategori_id);
            ViewBag.kullanici_id = new SelectList(db.kullanicilars, "kullanici_id", "ad_soyad", urunler.kullanici_id);
            ViewBag.memleket_id = new SelectList(db.memlekets, "memleket_id", "memleket_adi", urunler.memleket_id);
            return View(urunler);
        }

        // GET: AdminArsivSil/Delete/5
        public ActionResult Delete(int? id)
        {
            var urunler = db.urunlers.Where(u => u.urun_id == id).SingleOrDefault();

            if (urunler == null)
            {
                return HttpNotFound();
            }
            return View();
        }

        // POST: AdminArsivSil/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            urunler urunler = db.urunlers.Find(id);
            db.urunlers.Remove(urunler);
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
