namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("yorum")]
    public partial class yorum
    {
        public int yorumID { get; set; }

        [StringLength(50)]
        public string yorumIcerik { get; set; }

        public int? kullaniciID { get; set; }

        public int? urunID { get; set; }

        [Column(TypeName = "date")]
        public DateTime? tarih { get; set; }

        public virtual kullanicilar kullanicilar { get; set; }

        public virtual urunler urunler { get; set; }
    }
}
