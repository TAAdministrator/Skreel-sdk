package co.skreel.android.interfaces.cardlisteners;

import co.skreel.android.models.Meta;

public interface CardDeletedListener extends CardListener{
    void onDelete(Meta meta);
}
