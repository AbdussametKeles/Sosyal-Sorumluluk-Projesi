using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models;
using System.Web.Helpers;
using System.IO;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class UyeController : Controller
    {
        Model1 db = new Model1();
        // GET: Uye
        public ActionResult Index(int id)
        {
          
         

            return View(); 
        } 
         

        public ActionResult Login()
        {
            return View();
        } 
        [HttpPost]
        public ActionResult Login(kullanicilar uye)
        {
            var login = db.kullanicilars.Where(k => k.mail == uye.mail).SingleOrDefault();
            if(login.mail==uye.mail && login.sifre == uye.sifre)
            {
                Session["kullaniciid"] = login.kullaniciID;
                Session["mail"] = login.mail;
                Session["yetkiid"] = login.yetkiID;

                return RedirectToAction("Index", "Home");
                    
            }
            else
            {
                ViewBag.Uyari = "Kullanıcı Login Bilgilerinizi Kontrol Ediniz";
                return View();
            }


            
        } 
        public ActionResult Logout()
        {
            Session["kullaniciid"] = null;
            Session.Abandon();
            return RedirectToAction("Index", "Home");
        }


        public ActionResult Create()
        {
            return View();
        } 
          [HttpPost]
        public ActionResult Create(kullanicilar uye,HttpPostedFileBase resim)
        {
            if (ModelState.IsValid)
            { 
                if (resim != null)
                {
                    WebImage img = new WebImage(resim.InputStream);
                    FileInfo fotoinfo = new FileInfo(resim.FileName);

                    string newfoto = Guid.NewGuid().ToString() + fotoinfo.Extension;
                    img.Resize(150, 150);
                    img.Save("~/Uploads/resimler/" + newfoto);
                    uye.resim = "/Uploads/resimler/" + newfoto;
                    uye.yetkiID = 2;  
                    db.kullanicilars.Add(uye);
                    db.SaveChanges();
                    Session["kullaniciid"] = uye.kullaniciID;
                    Session["kullaniciadi"] = uye.kullaniciAdi;
                    return RedirectToAction("Index", "Home");
                    
                }
                else
                {
                    ModelState.AddModelError("Foto", "Foto Seçiniz:");
                }

            }    return View(uye);

        }
       

     

    }
}