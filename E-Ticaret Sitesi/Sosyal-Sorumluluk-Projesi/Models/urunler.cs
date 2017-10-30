namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("urunler")]
    public partial class urunler
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public urunler()
        {
            projes = new HashSet<proje>();
            etikets = new HashSet<etiket>();
        }

        [Key]
        public int urun_id { get; set; }

        public int? kategori_id { get; set; }

        [StringLength(50)]
        public string urun_adi { get; set; }

        [StringLength(500)]
        public string resim { get; set; }

        [StringLength(500)]
        public string urun_icerik { get; set; }

        [Column(TypeName = "date")]
        public DateTime? tarih { get; set; }

        public int? memleket_id { get; set; }

        public int? kullanici_id { get; set; }

        public virtual kategoriler kategoriler { get; set; }

        public virtual kullanicilar kullanicilar { get; set; }

        public virtual memleket memleket { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<proje> projes { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<etiket> etikets { get; set; }
    }
}
