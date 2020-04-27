package com.company.clinic.web.screens.owner;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Owner;

@UiController("clinic_Owner.browse")
@UiDescriptor("owner-browse.xml")
@LookupComponent("ownersTable")
@LoadDataBeforeShow
public class OwnerBrowse extends StandardLookup<Owner> {
}