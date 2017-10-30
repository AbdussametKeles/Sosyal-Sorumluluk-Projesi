using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Sosyal_Sorumluluk_Projesi.Models;

namespace Sosyal_Sorumluluk_Projesi.Controllers
{
    public class AdminProjeController : Controller
    {
        Model1 db = new Model1();
        // GET: AdminProje
        public ActionResult Index()
        {
            var projeler = db.projes.ToList();
            return View(projeler);
        }

        // GET: AdminProje/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: AdminProje/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: AdminProje/Create
        [HttpPost]
        public ActionResult Create(FormCollection collection)
        {
            try
            {
                // TODO: Add insert logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: AdminProje/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: AdminProje/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add update logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: AdminProje/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: AdminProje/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
