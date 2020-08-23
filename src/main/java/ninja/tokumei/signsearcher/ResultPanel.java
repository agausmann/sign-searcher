package ninja.tokumei.signsearcher;

import io.github.cottonmc.cotton.gui.widget.WBox;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import net.minecraft.block.entity.SignBlockEntity;

public class ResultPanel extends WBox {
    public ResultPanel() {
        super(Axis.HORIZONTAL);
    }

    public void setSign(SignBlockEntity sign) {

    }
}
