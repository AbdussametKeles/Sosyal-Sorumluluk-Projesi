using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models;
using PagedList;
using PagedList.Mvc;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class HomeController : Controller
    {
        Model1 db = new Model1();
        // GET: Home
        public ActionResult Index()
        { 
             
            var urun = db.urunlers.OrderByDescending(u => u.urunID).Take(6);
            return View(urun);
        }  

        public ActionResult Hakkimizda()
        {
            return View();
        } 

        public ActionResult Iletisim()
        {
            return View();
        }
        
        public ActionResult KategoriPartial()
        {
            return View(db.kategorilers.ToList()); 
        }
         
        public ActionResult GizlilikPolitikasi()
        {
            return View();

        }

        public ActionResult KullaniciSozlesmesi()
        {
            return View();

        }

        public JsonResult YorumYap(string yorum, int UrunID)
        {
            var kullaniciID = Session["kullaniciID"];
            if (yorum == null)
            {
                return Json(true, JsonRequestBehavior.AllowGet);
            }
            db.yorums.Add(new yorum { kullaniciID = Convert.ToInt32(kullaniciID), urunID = UrunID, yorumIcerik = yorum, tarih = DateTime.Now });
            db.SaveChanges();


            return Json(false, JsonRequestBehavior.AllowGet);
        }






    }
}