       01 MTG004-REGISTRO.
         05 MTG004-CDTABLA-L                   PIC  X(2).
         05 MTG004-CDTABLA-A                   PIC  X(1).
         05 MTG004-CDTABLA                     PIC  X(4).
         05 MTG004-NBTABLA-L                   PIC  X(2).
         05 MTG004-NBTABLA-A                   PIC  X(1).
         05 MTG004-NBTABLA                     PIC  X(30).
         05 MTG004-CLAVBAN-L                   PIC  X(2).
         05 MTG004-CLAVBAN-A                   PIC  X(1).
         05 MTG004-CLAVBAN                     PIC  X(4).
         05 MTG004-CLAVTG-L                    PIC  X(2).
         05 MTG004-CLAVTG-A                    PIC  X(1).
         05 MTG004-CLAVTG                      PIC  X(19).
         05 MTG004-TCCIDIOM-L                  PIC  X(2).
         05 MTG004-TCCIDIOM-A                  PIC  X(1).
         05 MTG004-TCCIDIOM                    PIC  X(1).
         05 MTG00401-COB-NAME-D
                            OCCURS 10  TIMES
                           INDEXED BY MTG00401-COB-NAME-D-INDEX.
           07 MTG004-CDSELEC-L                 PIC  X(2).
           07 MTG004-CDSELEC-A                 PIC  X(1).
           07 MTG004-CDSELEC                   PIC  X(1).
           07 MTG004-CLAVBANS-L                PIC  X(2).
           07 MTG004-CLAVBANS-A                PIC  X(1).
           07 MTG004-CLAVBANS                  PIC  X(4).
           07 MTG004-CLAVTABS-L                PIC  X(2).
           07 MTG004-CLAVTABS-A                PIC  X(1).
           07 MTG004-CLAVTABS                  PIC  X(20).
           07 MTG004-DATCLAV-L                 PIC  X(2).
           07 MTG004-DATCLAV-A                 PIC  X(1).
           07 MTG004-DATCLAV                   PIC  X(20).