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
    public class KategoriUrunController : Controller
    { Model1 db = new Model1();
        // GET: KategoriUrun
        public ActionResult Index(string sortOrder, string currentFilter, string searchString, int? page,int id)
        {
            ViewBag.CurrentSort = sortOrder; 
            ViewBag.NameSortParm = String.IsNullOrEmpty(sortOrder) ? "name_desc" : "";


            int pageSize = 3;
            int pageNumber = (page ?? 1);


             
            if (searchString != null)
            {
                page = 1;
            }
            else
            {
                searchString = currentFilter;
            } 

            ViewBag.CurrentFilter = searchString;

            var urun = from s in db.urunlers.Where(u=>u.kategoriID==id)
                       select s;
            if (!String.IsNullOrEmpty(searchString))
            {
                urun = urun.Where(s =>s.memleket.memleketAdi.Contains(searchString));
                                        

            }
            switch (sortOrder)
            {
                case "name_desc":
                    urun = urun.OrderByDescending(s => s.memleket.memleketAdi);
                    break;



                default:
                    urun = urun.OrderBy(s => s.memleket.memleketAdi);
                    break;
            } 
             
          
          

            return View(urun.ToPagedList(pageNumber, pageSize));
        }


        public ActionResult Detay(int id)
        {
            var urun = db.urunlers.Where(u => u.urunID == id).SingleOrDefault();

            return View(urun);

        }


    }
} 