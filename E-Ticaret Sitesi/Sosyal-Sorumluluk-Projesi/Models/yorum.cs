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
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public yorum()
        {
            urunlers = new HashSet<urunler>();
        }

        public int yorumID { get; set; }

        [StringLength(50)]
        public string yorumIcerik { get; set; }

        public int? kullaniciID { get; set; }

        public int? urunID { get; set; }

        public DateTime? tarih { get; set; }

        public virtual kullanicilar kullanicilar { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<urunler> urunlers { get; set; }

        public virtual urunler urunler { get; set; }
    }
}
