namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Model1 : DbContext
    {
        public Model1()
            : base("name=Model12")
        {
        }

        public virtual DbSet<kategoriler> kategorilers { get; set; }
        public virtual DbSet<kullanicilar> kullanicilars { get; set; }
        public virtual DbSet<proje> projes { get; set; }
        public virtual DbSet<urunler> urunlers { get; set; }
        public virtual DbSet<yetki> yetkis { get; set; }
        public virtual DbSet<yorum> yorums { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<kategoriler>()
                .Property(e => e.kategori_adi)
                .IsUnicode(false);

            modelBuilder.Entity<kullanicilar>()
                .Property(e => e.ad_soyad)
                .IsUnicode(false);

            modelBuilder.Entity<kullanicilar>()
                .Property(e => e.sifre)
                .IsUnicode(false);

            modelBuilder.Entity<kullanicilar>()
                .Property(e => e.mail)
                .IsUnicode(false);

            modelBuilder.Entity<kullanicilar>()
                .Property(e => e.telefon)
                .IsUnicode(false);

            modelBuilder.Entity<kullanicilar>()
                .Property(e => e.resim)
                .IsUnicode(false);

            modelBuilder.Entity<proje>()
                .Property(e => e.resim)
                .IsUnicode(false);

            modelBuilder.Entity<urunler>()
                .Property(e => e.urun_adi)
                .IsUnicode(false);

            modelBuilder.Entity<urunler>()
                .Property(e => e.resim)
                .IsUnicode(false);

            modelBuilder.Entity<urunler>()
                .Property(e => e.urun_icerik)
                .IsUnicode(false);

            modelBuilder.Entity<yetki>()
                .Property(e => e.yetki_adi)
                .IsUnicode(false);

            modelBuilder.Entity<yorum>()
                .Property(e => e.icerik)
                .IsUnicode(false);
        }
    }
}
