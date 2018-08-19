package start.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@TableGenerator(
        name = "ACCOUNT_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "ACCOUNT_SEQ",
        allocationSize = 1
)
@Data
public class Account {

    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "ACCOUNT_SEQ_GENERATOR")
    private Long id;

    private String data;
}
