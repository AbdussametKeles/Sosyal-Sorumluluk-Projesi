using Sosyal_Sorumluluk_Projesi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class iletisimController : Controller
    {
        // GET: iletisim
        public ActionResult Index()
        {
            return View();
        }
         
        [HttpPost]
        public ActionResult Index(iletisimModel model)
        {
            if (ModelState.IsValid) 
            {
                var body = new StringBuilder();
                body.AppendLine("Rumuz: " + model.NickName);
                body.AppendLine("İsim: " + model.FullName);
                body.AppendLine("Tel: " + model.Phone);
                body.AppendLine("Eposta: " + model.Email);
                body.AppendLine("Konu: " + model.Message);
                Gmail.SendMail(body.ToString());
                ViewBag.Success = true;
            }



            return View();
        } 



    }
}