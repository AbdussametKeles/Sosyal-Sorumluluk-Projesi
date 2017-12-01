using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class AdminController : Controller
    { Model1 db = new Model1();
        // GET: Admin
        public ActionResult Index()
        {

            ViewBag.urunSayisi = db.urunlers.Count();
            ViewBag.yorumSayisi = db.yorums.Count();
            ViewBag.kategoriSayisi = db.kategorilers.Count();
            ViewBag.uyeSayisi = db.kullanicilars.Count();
                
            return View(); 
        } 




    }
}