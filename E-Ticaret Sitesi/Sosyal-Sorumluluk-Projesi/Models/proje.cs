namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("proje")]
    public partial class proje
    {
        [Key]
        public int proje_id { get; set; }

        public int? kullanici_id { get; set; }

        public int? urun_id { get; set; }

        public DateTime? tarih { get; set; }

        public int? yorum_id { get; set; }

        [StringLength(500)]
        public string resim { get; set; }

        public int? kategori_id { get; set; }

        public virtual kategoriler kategoriler { get; set; }

        public virtual kullanicilar kullanicilar { get; set; }

        public virtual urunler urunler { get; set; }

        public virtual yorum yorum { get; set; }
    }
}
