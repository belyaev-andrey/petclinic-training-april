-- begin CLINIC_OWNER
create table CLINIC_OWNER (
    ID varchar(36) not null,
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
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    USER_ID varchar(36) not null,
    HOURLY_RATE decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end CLINIC_VETERINARIAN
-- begin CLINIC_PET_TYPE
create table CLINIC_PET_TYPE (
    ID varchar(36) not null,
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
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    TYPE_ID varchar(36) not null,
    OWNER_ID varchar(36),
    --
    primary key (ID)
)^
-- end CLINIC_PET
-- begin CLINIC_VISIT
create table CLINIC_VISIT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PET_ID varchar(36) not null,
    VETERINARIAN_ID varchar(36) not null,
    DATE_ timestamp not null,
    DESCRIPTION longvarchar,
    HOURS_SPENT integer not null,
    AMOUNT decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end CLINIC_VISIT
-- begin CLINIC_CONSUMABLE
create table CLINIC_CONSUMABLE (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(255) not null,
    DESCRIPTION longvarchar,
    PRICE decimal(19, 2) not null,
    --
    primary key (ID)
)^
-- end CLINIC_CONSUMABLE
-- begin CLINIC_VISIT_CONSUMABLE_LINK
create table CLINIC_VISIT_CONSUMABLE_LINK (
    VISIT_ID varchar(36) not null,
    CONSUMABLE_ID varchar(36) not null,
    primary key (VISIT_ID, CONSUMABLE_ID)
)^
-- end CLINIC_VISIT_CONSUMABLE_LINK
