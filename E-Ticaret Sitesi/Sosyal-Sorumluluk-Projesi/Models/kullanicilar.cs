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
            Arsivs = new HashSet<Arsiv>();
            urunlers = new HashSet<urunler>();
            yorums = new HashSet<yorum>();
        }

        [Key]
        public int kullaniciID { get; set; }

        public int? yetkiID { get; set; }

        public int? memleketID { get; set; }

        [StringLength(30)]
        public string adsoyad { get; set; }

        [StringLength(20)]
        public string kullaniciAdi { get; set; }

        [StringLength(25)]
        public string mail { get; set; }

        [StringLength(200)]
        public string sifre { get; set; }

        [StringLength(11)]
        public string telefon { get; set; }

        [StringLength(500)]
        public string resim { get; set; }

        [StringLength(50)]
        public string gizliSoruCevap { get; set; }

        [StringLength(50)]
        public string gizliSoru { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Arsiv> Arsivs { get; set; }

        public virtual memleket memleket { get; set; }

        public virtual yetki yetki { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<urunler> urunlers { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<yorum> yorums { get; set; }
    }
}
