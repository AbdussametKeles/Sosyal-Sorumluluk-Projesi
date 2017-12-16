namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Arsiv")]
    public partial class Arsiv
    {
        public int ArsivID { get; set; }

        public int? urunID { get; set; }

        public int? kategoriID { get; set; }

        [StringLength(50)]
        public string urunAdi { get; set; }

        [StringLength(500)]
        public string urunİcerik { get; set; }

        public int? memleketID { get; set; }

        [Column(TypeName = "date")]
        public DateTime? tarih { get; set; }

        [StringLength(500)]
        public string resim { get; set; }

        public int? kullaniciID { get; set; }

        public virtual kategoriler kategoriler { get; set; }

        public virtual kullanicilar kullanicilar { get; set; }

        public virtual memleket memleket { get; set; }

        public virtual urunler urunler { get; set; }
    }
}
