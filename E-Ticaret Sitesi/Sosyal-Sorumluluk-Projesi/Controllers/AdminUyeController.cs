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
    public class AdminUyeController : Controller
    {
        private Model1 db = new Model1();

        // GET: AdminUye
        public ActionResult Index()
        {
            var kullanicilars = db.kullanicilars.Include(k => k.memleket).Include(k => k.yetki);
            return View(kullanicilars.ToList());
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
        public ActionResult Create([Bind(Include = "kullaniciID,yetkiID,memleketID,adsoyad,kullaniciAdi,mail,sifre,telefon,resim")] kullanicilar kullanicilar)
        {
            if (ModelState.IsValid)
            {
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
        public ActionResult Edit([Bind(Include = "kullaniciID,yetkiID,memleketID,adsoyad,kullaniciAdi,mail,sifre,telefon,resim")] kullanicilar kullanicilar)
        {
            if (ModelState.IsValid)
            {
                db.Entry(kullanicilar).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.memleketID = new SelectList(db.memlekets, "memleketID", "memleketAdi", kullanicilar.memleketID);
            ViewBag.yetkiID = new SelectList(db.yetkis, "yetkiID", "yetkiAdi", kullanicilar.yetkiID);
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
