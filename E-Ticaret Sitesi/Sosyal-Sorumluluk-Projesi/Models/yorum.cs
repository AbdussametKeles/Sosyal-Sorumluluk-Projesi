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
            projes = new HashSet<proje>();
        }

        [Key]
        public int yorum_id { get; set; }

        [StringLength(500)]
        public string icerik { get; set; }

        public int? kullanici_id { get; set; }

        public int? urun_id { get; set; }

        public virtual kullanicilar kullanicilar { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<proje> projes { get; set; }

        public virtual urunler urunler { get; set; }
    }
}
