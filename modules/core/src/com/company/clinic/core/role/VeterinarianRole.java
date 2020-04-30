package com.company.clinic.core.role;

import com.company.clinic.entity.Consumable;
import com.company.clinic.entity.Owner;
import com.company.clinic.entity.Pet;
import com.company.clinic.entity.PetType;
import com.company.clinic.entity.Veterinarian;
import com.company.clinic.entity.Visit;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = VeterinarianRole.NAME)
public class VeterinarianRole extends AnnotatedRoleDefinition {
    public final static String NAME = "veterinarian-access";

    @ScreenAccess(screenIds = {"clinic_Consumable.browse", "application-clinic", "clinic_Visit.browse", "clinic_UsedConsumables", "clinic_Visit.edit", "clinic_StartTreatmentScreen", "clinic_DoTreatmentScreen"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Visit.class, operations = {EntityOp.UPDATE, EntityOp.READ})
    @EntityAccess(entityClass = Veterinarian.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = PetType.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Pet.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Owner.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Consumable.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Consumable.class, view = {"description", "title"})
    @EntityAttributeAccess(entityClass = Visit.class, modify = {"hoursSpent", "consumables"}, view = {"pet", "veterinarian", "date", "description", "amount"})
    @EntityAttributeAccess(entityClass = Veterinarian.class, view = "*")
    @EntityAttributeAccess(entityClass = PetType.class, view = "*")
    @EntityAttributeAccess(entityClass = Pet.class, view = "*")
    @EntityAttributeAccess(entityClass = Owner.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }
}
