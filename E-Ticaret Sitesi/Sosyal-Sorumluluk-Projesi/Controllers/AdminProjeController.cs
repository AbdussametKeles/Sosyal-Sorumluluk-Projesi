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
    public class AdminProjeController : Controller
    {
        public Model1 db = new Model1();

        // GET: AdminProje
        public ActionResult Index()
        {
            var projes = db.projes.Include(p => p.kategoriler).Include(p => p.kullanicilar).Include(p => p.urunler).Include(p => p.yorum);
            return View(projes.ToList());
        }

        // GET: AdminProje/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            proje proje = db.projes.Find(id);
            if (proje == null)
            {
                return HttpNotFound();
            }
            return View(proje);
        }

        // GET: AdminProje/Create
        public ActionResult Create()
        {
            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi");
            ViewBag.kullanici_id = new SelectList(db.kullanicilars, "kullanici_id", "ad_soyad");
            ViewBag.urun_id = new SelectList(db.urunlers, "urun_id", "urun_adi");
            ViewBag.yorum_id = new SelectList(db.yorums, "yorum_id", "icerik");
            return View();
        }

        // POST: AdminProje/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "proje_id,kullanici_id,urun_id,tarih,yorum_id,resim,kategori_id")] proje proje)
        {
            if (ModelState.IsValid)
            {
                db.projes.Add(proje);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi", proje.kategori_id);
            ViewBag.kullanici_id = new SelectList(db.kullanicilars, "kullanici_id", "ad_soyad", proje.kullanici_id);
            ViewBag.urun_id = new SelectList(db.urunlers, "urun_id", "urun_adi", proje.urun_id);
            ViewBag.yorum_id = new SelectList(db.yorums, "yorum_id", "icerik", proje.yorum_id);
            return View(proje);
        }

        // GET: AdminProje/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            proje proje = db.projes.Find(id);
            if (proje == null)
            {
                return HttpNotFound();
            }
            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi", proje.kategori_id);
            ViewBag.kullanici_id = new SelectList(db.kullanicilars, "kullanici_id", "ad_soyad", proje.kullanici_id);
            ViewBag.urun_id = new SelectList(db.urunlers, "urun_id", "urun_adi", proje.urun_id);
            ViewBag.yorum_id = new SelectList(db.yorums, "yorum_id", "icerik", proje.yorum_id);
            return View(proje);
        }

        // POST: AdminProje/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "proje_id,kullanici_id,urun_id,tarih,yorum_id,resim,kategori_id")] proje proje)
        {
            if (ModelState.IsValid)
            {
                db.Entry(proje).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.kategori_id = new SelectList(db.kategorilers, "kategori_id", "kategori_adi", proje.kategori_id);
            ViewBag.kullanici_id = new SelectList(db.kullanicilars, "kullanici_id", "ad_soyad", proje.kullanici_id);
            ViewBag.urun_id = new SelectList(db.urunlers, "urun_id", "urun_adi", proje.urun_id);
            ViewBag.yorum_id = new SelectList(db.yorums, "yorum_id", "icerik", proje.yorum_id);
            return View(proje);
        }

        // GET: AdminProje/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            proje proje = db.projes.Find(id);
            if (proje == null)
            {
                return HttpNotFound();
            }
            return View(proje);
        }

        // POST: AdminProje/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            proje proje = db.projes.Find(id);
            db.projes.Remove(proje);
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
