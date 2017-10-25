namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("yetki")]
    public partial class yetki
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public yetki()
        {
            kullanicilars = new HashSet<kullanicilar>();
        }

        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int yetki_id { get; set; }

        [StringLength(10)]
        public string yetki_adi { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<kullanicilar> kullanicilars { get; set; }
    }
}
