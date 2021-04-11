@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_entity")
public class MyEntity {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // @Id
    // @Column(name = "id", unique = true)
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // @Type(type = "uuid-char")
    // private UUID id;
}