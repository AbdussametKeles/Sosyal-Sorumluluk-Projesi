using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Web;

namespace Sosyal_Sorumluluk_Projesi.Models
{
    public static class Gmail
    { 
        public static void SendMail(string body)
        {
            var fromAddress = new MailAddress("mehmetnurullahkocak95@gmail.com", "Admine Gelen Soru");
            var toAddress = new MailAddress("mehmetnurullahkocak95@gmail.com");
            const string subject = "Admine Gelen Soru";

            using (var smtp = new SmtpClient
            {
                Host = "smtp.gmail.com",
                Port = 587,
                EnableSsl = true,
                DeliveryMethod = SmtpDeliveryMethod.Network,
                UseDefaultCredentials = false,
                Credentials = new NetworkCredential(fromAddress.Address, "menukogs1995")
                //trololol kısmı e-posta adresinin şifresi
            })
            {
                using (var message = new MailMessage(fromAddress, toAddress) { Subject = subject, Body = body })
                { 
                    smtp.Send(message);
                }
            }
        }
    }
}