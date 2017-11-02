namespace Sosyal_Sorumluluk_Projesi.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("arsiv")]
    public partial class arsiv
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        public int urun_id { get; set; }
    }
}
