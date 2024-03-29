package ee.coo.mobile.tile

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import androidx.wear.tiles.DeviceParametersBuilders
import androidx.wear.tiles.DimensionBuilders
import androidx.wear.tiles.LayoutElementBuilders
import androidx.wear.tiles.LayoutElementBuilders.Box
import androidx.wear.tiles.LayoutElementBuilders.Column
import androidx.wear.tiles.LayoutElementBuilders.HORIZONTAL_ALIGN_CENTER
import androidx.wear.tiles.LayoutElementBuilders.VERTICAL_ALIGN_CENTER
import androidx.wear.tiles.ResourceBuilders.Resources
import androidx.wear.tiles.material.Colors
import com.google.android.horologist.tiles.render.SingleTileLayoutRenderer
import ee.coo.wear.compose.theme.CooeeColorPalette

class CooeeTileRenderer(context: Context) :
    SingleTileLayoutRenderer<Any, Unit>(context) {
    override fun createTheme(): Colors {
        return Colors(
            CooeeColorPalette.primary.toArgb(),
            CooeeColorPalette.onPrimary.toArgb(),
            CooeeColorPalette.surface.toArgb(),
            CooeeColorPalette.onSurface.toArgb(),
        )
    }

    override fun renderTile(
        state: Any,
        deviceParameters: DeviceParametersBuilders.DeviceParameters,
    ): LayoutElementBuilders.LayoutElement {
        return Box.Builder()
            .setWidth(DimensionBuilders.expand())
            .setHeight(DimensionBuilders.expand())
            .setVerticalAlignment(VERTICAL_ALIGN_CENTER)
            .addContent(
                Column.Builder()
                    .setHorizontalAlignment(HORIZONTAL_ALIGN_CENTER)
                    .setWidth(DimensionBuilders.expand())
                    .apply {

                    }.build()
            ).build()
    }

    override fun Resources.Builder.produceRequestedResources(
        resourceState: Unit,
        deviceParameters: DeviceParametersBuilders.DeviceParameters,
        resourceIds: MutableList<String>,
    ) {
    }
}
