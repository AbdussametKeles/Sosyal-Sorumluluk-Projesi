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

            var ViewDataurunID = ViewData["urunID"];
            var ViewDatakategoriID = ViewData["kategoriID"];
            var ViewDataurunAdi = ViewData["urunAdi"];
            var ViewDataresim = ViewData["resim"];
            var ViewDataurunİcerik = ViewData["urunİcerik"];
            var ViewDatatarih = ViewData["tarih"];
            var ViewDatamemleketID = ViewData["memleketID"];
            var ViewDatakullaniciID = ViewData["kullaniciID"];

             
        }

     
        // GET: AdminArsivSil/Delete/5
        public ActionResult Delete(int? id)
        {
            var urunler = db.urunlers.Where(u => u.urunID == id).SingleOrDefault();

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
