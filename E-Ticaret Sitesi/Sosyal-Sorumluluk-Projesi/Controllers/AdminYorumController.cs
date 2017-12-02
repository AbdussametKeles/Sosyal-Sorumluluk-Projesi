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
    public class AdminYorumController : Controller
    {
        private Model1 db = new Model1();

        // GET: AdminYorum
        public ActionResult Index()
        {
            var yorums = db.yorums.Include(y => y.kullanicilar).Include(y => y.urunler);
            return View(yorums.OrderByDescending(y=>y.yorumID).ToList());
        } 
         
        // GET: AdminYorum/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            yorum yorum = db.yorums.Find(id);
            if (yorum == null)
            {
                return HttpNotFound();
            }
            return View(yorum);
        }

        // GET: AdminYorum/Create
        public ActionResult Create()
        {
            ViewBag.kullaniciID = new SelectList(db.kullanicilars, "kullaniciID", "adsoyad");
            ViewBag.urunID = new SelectList(db.urunlers, "urunID", "urunAdi");
            return View();
        }

        // POST: AdminYorum/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "yorumID,yorumIcerik,kullaniciID,urunID,tarih")] yorum yorum)
        {
            if (ModelState.IsValid)
            {
                db.yorums.Add(yorum);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.kullaniciID = new SelectList(db.kullanicilars, "kullaniciID", "adsoyad", yorum.kullaniciID);
            ViewBag.urunID = new SelectList(db.urunlers, "urunID", "urunAdi", yorum.urunID);
            return View(yorum);
        }

        // GET: AdminYorum/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            yorum yorum = db.yorums.Find(id);
            if (yorum == null)
            {
                return HttpNotFound();
            }
            ViewBag.kullaniciID = new SelectList(db.kullanicilars, "kullaniciID", "adsoyad", yorum.kullaniciID);
            ViewBag.urunID = new SelectList(db.urunlers, "urunID", "urunAdi", yorum.urunID);
            return View(yorum);
        }

        // POST: AdminYorum/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "yorumID,yorumIcerik,kullaniciID,urunID,tarih")] yorum yorum)
        {
            if (ModelState.IsValid)
            {
                db.Entry(yorum).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.kullaniciID = new SelectList(db.kullanicilars, "kullaniciID", "adsoyad", yorum.kullaniciID);
            ViewBag.urunID = new SelectList(db.urunlers, "urunID", "urunAdi", yorum.urunID);
            return View(yorum);
        }

        // GET: AdminYorum/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            yorum yorum = db.yorums.Find(id);
            if (yorum == null)
            {
                return HttpNotFound();
            }
            return View(yorum);
        }

        // POST: AdminYorum/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            yorum yorum = db.yorums.Find(id);
            db.yorums.Remove(yorum);
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
