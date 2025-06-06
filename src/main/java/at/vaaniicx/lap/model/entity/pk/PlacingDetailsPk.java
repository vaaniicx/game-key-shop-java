package at.vaaniicx.lap.model.entity.pk;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PlacingDetailsPk implements Serializable {

    @Column(name = "placing_id")
    private Long placingId;

    @Column(name = "key_code_id")
    private Long keyCodeId;
}
