public class HelperClass {

    public static boolean setUserData(Context context, RealmModel realmModel) {

        RealmModel realmModelObject;

        Realm.init(context);

        Realm realm = Realm.getDefaultInstance();

        if (!realm.isInTransaction()) {

            realm.beginTransaction();

            if (realmModel.getPrimaryKey() != 0) {

                int primary = realmModel.getPrimaryKey();

                realmModelObject = realm.where(RealmModel.class)
                        .equalTo("primaryKey", primary).findFirst();

                realmModelObject.setEmpId(realmModel.getEmpId());

                realmModelObject.setEmpName(realmModel.getEmpName());

            } else {

                int primary = 0;

                try {

                    primary = realm.where(RealmModel.class)
                            .max("primaryKey")
                            .intValue() + 1;

                } catch (Exception e) {

                    e.printStackTrace();

                }

                realmModelObject = realm.createObject(RealmModel.class, primary);

                realmModelObject.setEmpName(realmModel.getEmpName());

                realmModelObject.setEmpId(realmModel.getEmpId());

            }

        }

        if (!realm.isClosed()) {

            realm.commitTransaction();

            realm.close();

        }

        return true;

    }

    public static List<RealmModel> getUserDate(Context context) {

        Realm.init(context);

        Realm realm = Realm.getDefaultInstance();

        RealmResults<RealmModel> realmResults = realm.where(RealmModel.class)
                .sort("primaryKey")
                .findAll();

        return new ArrayList<>(realm.copyFromRealm(realmResults));

    }

    public static boolean deleteData(Context context, int primaryKey) {

        Realm.init(context);

        Realm realm = Realm.getDefaultInstance();

        RealmModel realmModel = realm.where(RealmModel.class)
                .equalTo("primaryKey", primaryKey)
                .findFirst();

        if (realmModel != null) {

            if (!realm.isInTransaction()) {

                realm.beginTransaction();

                realmModel.deleteFromRealm();

            }


            if (!realm.isClosed()) {

                realm.commitTransaction();

                realm.close();

            }

            return true;

        }

        return false;

    }

}
