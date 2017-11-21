using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models; 


namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class HomeController : Controller
    {
        Model1 db = new Model1();
        // GET: Home
        public ActionResult Index()
        {

            var urun = db.urunlers.Where(u => u.urunID > 0);
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

      

    }
}