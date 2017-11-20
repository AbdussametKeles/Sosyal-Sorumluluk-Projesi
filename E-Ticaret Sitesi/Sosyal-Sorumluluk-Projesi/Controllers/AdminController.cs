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
            return View(); 
        } 




    }
}