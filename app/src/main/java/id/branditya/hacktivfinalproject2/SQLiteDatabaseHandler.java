package id.branditya.hacktivfinalproject2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appData";
    private static final String TABEL_STAFF = "Staff";
    private static final String TABEL_ADMIN = "Admin";
    private static final String TABEL_PRODUCT = "Product";
    private static final String TABEL_USER = "User";

    private static final String KEY_ID = "id";
    private static final String STAFF_NAME = "Staff_name";
    private static final String ADMIN_NAME = "Admin_name";
    private static final String USER_NAME = "User_name";
    private static final String USER_PHONE_NUMBER = "User_phone_number";
    private static final String STAFF_PASSWORD = "Staff_password";
    private static final String ADMIN_PASSWORD = "Admin_password";
    private static final String USER_PASSWORD = "User_password";
    private static final String PRODUCT_TYPE = "Product_type";
    private static final String PRODUCT_FILTER = "Product_filter";
    private static final String PRODUCT_CATEGORY = "Product_category";
    private static final String PRODUCT_NAME = "Product_name";
    private static final String PRODUCT_QUANTITY = "Product_quantity";

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_STAFF_TABLE = "CREATE TABLE " + TABEL_STAFF + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + STAFF_NAME + " TEXT," + STAFF_PASSWORD + " TEXT" + ")";
        String CREATE_ADMIN_TABLE = "CREATE TABLE " + TABEL_ADMIN + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + ADMIN_NAME + " TEXT," + ADMIN_PASSWORD + " TEXT" + ")";
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABEL_PRODUCT + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + PRODUCT_TYPE + " TEXT," + PRODUCT_FILTER + " TEXT," +
                PRODUCT_CATEGORY + " TEXT," + PRODUCT_NAME + " TEXT," + PRODUCT_QUANTITY + " INTEGER" + ")";
        String CREATE_USER_TABLE = "CREATE TABLE " + TABEL_USER + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + USER_NAME + " TEXT," + USER_PHONE_NUMBER + " TEXT," +
                USER_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_STAFF_TABLE);
        sqLiteDatabase.execSQL(CREATE_ADMIN_TABLE);
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        ContentValues valuesAdmin = new ContentValues();
        valuesAdmin.put(ADMIN_NAME, "admin123");
        valuesAdmin.put(ADMIN_PASSWORD, "123456");
        sqLiteDatabase.insert(TABEL_ADMIN, null, valuesAdmin);

        Product product1 = new Product("Clothing", "Mens", "Tshirt", "Tshirt Men", 0);
        Product product2 = new Product("Clothing", "Mens", "Formal", "Formal Men", 0);
        Product product3 = new Product("Clothing", "Woman", "Tshirt", "Tshirt Woman", 0);
        Product product4 = new Product("Clothing", "Woman", "Formal", "Formal Woman", 0);
        Product product5 = new Product("Electronic", "Computer", "", "Computer 1", 0);
        Product product6 = new Product("Electronic", "Smartphone", "", "Smartphone 1", 0);
        Product product7 = new Product("Book", "", "", "Book 1", 0);
        Product product8 = new Product("Other", "", "", "Other 1", 0);
        addProduct(sqLiteDatabase, product1);
        addProduct(sqLiteDatabase, product2);
        addProduct(sqLiteDatabase, product3);
        addProduct(sqLiteDatabase, product4);
        addProduct(sqLiteDatabase, product5);
        addProduct(sqLiteDatabase, product6);
        addProduct(sqLiteDatabase, product7);
        addProduct(sqLiteDatabase, product8);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABEL_STAFF);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABEL_ADMIN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABEL_PRODUCT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABEL_USER);
        onCreate(sqLiteDatabase);
    }

    public void addStaff(Staff staff) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STAFF_NAME, staff.getName());
        values.put(STAFF_PASSWORD, staff.getPassword());

        db.insert(TABEL_STAFF, null, values);
        db.close();
    }

    public void addProduct(SQLiteDatabase sqLiteDatabase, Product product) {

        ContentValues values = new ContentValues();
        values.put(PRODUCT_TYPE, product.getType());
        values.put(PRODUCT_FILTER, product.getFilter());
        values.put(PRODUCT_CATEGORY, product.getCategory());
        values.put(PRODUCT_NAME, product.getName());
        values.put(PRODUCT_QUANTITY, product.getQuantity());

        sqLiteDatabase.insert(TABEL_PRODUCT, null, values);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(USER_PHONE_NUMBER, user.getPhoneNumber());
        values.put(USER_PASSWORD, user.getPassword());

        db.insert(TABEL_USER, null, values);
        db.close();
    }

    public void editProductQuantity(int productId, int productQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_QUANTITY = "UPDATE " + TABEL_PRODUCT + " SET " + PRODUCT_QUANTITY + " = " +
                productQuantity + " WHERE id = " + productId;
        db.execSQL(UPDATE_QUANTITY);
    }

    public List<Staff> getAllStaff() {
        List<Staff> taskList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABEL_STAFF;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff();
                staff.setId(Integer.parseInt(cursor.getString(0)));
                staff.setName(cursor.getString(1));
                staff.setPassword(cursor.getString(2));
                taskList.add(staff);
            } while (cursor.moveToNext());
        }
        return taskList;
    }

    //getting single staff
    public Staff getStaff(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABEL_STAFF, new String[]{KEY_ID, STAFF_NAME, STAFF_PASSWORD}, KEY_ID
                + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Staff staff = new Staff(cursor.getString(1), cursor.getString(2));

        return staff;
    }

    //getting single admin
    public Admin getAdmin(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABEL_ADMIN, new String[]{KEY_ID, ADMIN_NAME, ADMIN_PASSWORD}, KEY_ID
                + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Admin admin = new Admin(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return admin;
    }

    public List<Admin> getAllAdmin() {
        List<Admin> adminList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABEL_ADMIN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Admin admin = new Admin();
                admin.setId(Integer.parseInt(cursor.getString(0)));
                admin.setName(cursor.getString(1));
                admin.setPassword(cursor.getString(2));
                adminList.add(admin);
            } while (cursor.moveToNext());
        }
        return adminList;
    }

    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABEL_PRODUCT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setType(cursor.getString(1));
                product.setFilter(cursor.getString(2));
                product.setCategory(cursor.getString(3));
                product.setName(cursor.getString(4));
                product.setQuantity(Integer.parseInt(cursor.getString(5)));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        return productList;
    }

    public List<Product> getProductByType(String productType) {
        List<Product> productList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABEL_PRODUCT + " WHERE " + PRODUCT_TYPE +
                "=" + "'" + productType + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setType(cursor.getString(1));
                product.setFilter(cursor.getString(2));
                product.setCategory(cursor.getString(3));
                product.setName(cursor.getString(4));
                product.setQuantity(Integer.parseInt(cursor.getString(5)));
                productList.add(product);
            } while (cursor.moveToNext());
        }
        return productList;
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABEL_USER;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setPhoneNumber(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    //getting single product
    public Product getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABEL_PRODUCT, new String[]{KEY_ID, PRODUCT_TYPE, PRODUCT_FILTER,
                PRODUCT_CATEGORY, PRODUCT_NAME, PRODUCT_QUANTITY}, KEY_ID
                + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Product product = new Product(cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)));

        return product;
    }
}
