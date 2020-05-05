-- begin CLINIC_OWNER
create table CLINIC_OWNER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    PHONE_NUMBER varchar(255) not null,
    EMAIL varchar(255) not null,
    --
    primary key (ID)
)^
-- end CLINIC_OWNER
-- begin CLINIC_VETERINARIAN
create table CLINIC_VETERINARIAN (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID uuid not null,
    HOURLY_RATE decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end CLINIC_VETERINARIAN
-- begin CLINIC_PET_TYPE
create table CLINIC_PET_TYPE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end CLINIC_PET_TYPE
-- begin CLINIC_PET
create table CLINIC_PET (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    TYPE_ID uuid not null,
    OWNER_ID uuid,
    --
    primary key (ID)
)^
-- end CLINIC_PET
-- begin CLINIC_VISIT
create table CLINIC_VISIT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ bigint,
    PET_ID uuid not null,
    VETERINARIAN_ID uuid not null,
    DATE_ timestamp not null,
    DESCRIPTION text,
    HOURS_SPENT integer not null,
    AMOUNT decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end CLINIC_VISIT
-- begin CLINIC_CONSUMABLE
create table CLINIC_CONSUMABLE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(255) not null,
    DESCRIPTION text,
    PRICE decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end CLINIC_CONSUMABLE
-- begin CLINIC_VISIT_CONSUMABLE_LINK
create table CLINIC_VISIT_CONSUMABLE_LINK (
    VISIT_ID uuid,
    CONSUMABLE_ID uuid,
    primary key (VISIT_ID, CONSUMABLE_ID)
)^
-- end CLINIC_VISIT_CONSUMABLE_LINK
