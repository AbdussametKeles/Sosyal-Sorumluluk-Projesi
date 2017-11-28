using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class UrunlerController : Controller
    {
        Model1 db = new Model1();
        // GET: Urunler
        public ActionResult Index()
        {
            return View();
        } 

        public ActionResult Detay(int id)
        {
            var urun = db.urunlers.Where(u => u.urunID == id).SingleOrDefault(); 

            return View(urun);

        }


    }
}