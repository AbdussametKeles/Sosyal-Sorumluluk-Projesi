namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("kullanicilar")]
    public partial class kullanicilar
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public kullanicilar()
        {
            projes = new HashSet<proje>();
            yorums = new HashSet<yorum>();
        }

        [Key]
        public int kullanici_id { get; set; }

        public int? yetki_id { get; set; }

        [StringLength(25)]
        public string ad_soyad { get; set; }

        [StringLength(20)]
        public string sifre { get; set; }

        [StringLength(30)]
        public string mail { get; set; }

        [StringLength(11)]
        public string telefon { get; set; }

        [StringLength(500)]
        public string resim { get; set; }

        public virtual yetki yetki { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<proje> projes { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<yorum> yorums { get; set; }
    }
}
