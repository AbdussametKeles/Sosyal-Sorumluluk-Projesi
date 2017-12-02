namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Model1 : DbContext
    {
        public Model1()
            : base("name=Model28")
        {
        }

        public virtual DbSet<kategoriler> kategorilers { get; set; }
        public virtual DbSet<kullanicilar> kullanicilars { get; set; }
        public virtual DbSet<memleket> memlekets { get; set; }
        public virtual DbSet<urunler> urunlers { get; set; }
        public virtual DbSet<yetki> yetkis { get; set; }
        public virtual DbSet<yorum> yorums { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<urunler>()
                .HasMany(e => e.yorums)
                .WithOptional(e => e.urunler)
                .HasForeignKey(e => e.urunID);

            modelBuilder.Entity<yorum>()
                .HasMany(e => e.urunlers)
                .WithOptional(e => e.yorum)
                .HasForeignKey(e => e.yorumID);
        }
    }
}
