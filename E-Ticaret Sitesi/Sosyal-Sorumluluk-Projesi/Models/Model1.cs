namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Model1 : DbContext
    {
        public Model1()
            : base("name=Model25")
        {
        }

        public virtual DbSet<etiket> etikets { get; set; }
        public virtual DbSet<kategoriler> kategorilers { get; set; }
        public virtual DbSet<kullanicilar> kullanicilars { get; set; }
        public virtual DbSet<memleket> memlekets { get; set; }
        public virtual DbSet<urunler> urunlers { get; set; }
        public virtual DbSet<yetki> yetkis { get; set; }
        public virtual DbSet<yorum> yorums { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<etiket>()
                .HasMany(e => e.urunlers)
                .WithMany(e => e.etikets)
                .Map(m => m.ToTable("urunEtiket").MapLeftKey("etiketID").MapRightKey("urunID"));
        }
    }
}
