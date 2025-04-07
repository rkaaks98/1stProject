package farmstory.util;

public final class Query {
  public static final String INSERT_USER =
      "INSERT INTO `user` VALUES(?,SHA2(?, 256),?,?,0,0,?,?,?,?,?,NOW(),NULL);";
  public static final String SELECT_USER = "SELECT * FROM `user` WHERE `id` = ?;";
  public static final String SELECT_ALL_USER = "SELECT * FROM `user`;";
  public static final String COUNT_USER_WITH_COLNAME =
      "SELECT COUNT(?) FROM `user` WHERE `id` = ?;";
  public static final String COUNT_USER = "SELECT COUNT(`id`) FROM `user`;";
  public static final String UPDATE_USER =
      "UPDATE `user` SET `password`=?, `name`=?, `nickname`=?, `point`=?, `level`=?, `email`=?, `phone_num`=?, `zip`=?, `address`=?, `address_detail`=? WHERE `id`=?;";
  public static final String DELETE_USER = "DELETE FROM `user` WHERE `id`=?;";

  public static final String INSERT_TERM = "INSERT INTO `term` VALUES(?, ?, ?);";
  public static final String SELECT_TERM = "SEELCT * FROM `term` WHERE `id` = ?;";
  public static final String SELECT_ALL_TERMS = "SELECT * FROM `term`;";
  public static final String UPDATE_TERM = "UPDATE `term` SET `title`=? `content`=? WHERE `id`=?;";
  public static final String DELETE_TERM = "DELETE FROM `term` WHERE `id` = ?;";

  public static final String INSERT_PRODUCT =
      "INSERT INTO `product` (`company_id`, `name`, `category`, `price`, `point`, `discount_rate`, `delivery_fee`, `stock`, `image_id`, `register_date`) VALUES (?,?,?,?,?,?,?,?,?, NOW());";
  public static final String SELECT_PRODUCT = "SELECT " + "prod.id, " + "comp.id AS `company_id`, "
      + "comp.company_name, " + "comp.manager_name, " + "comp.contact, " + "comp.addr, "
      + "prod.`name`, " + "prod.category, " + "prod.price, " + "prod.`point`, "
      + "prod.discount_rate, " + "prod.delivery_fee, " + "prod.stock, " + "image.id AS `image_id`, "
      + "image.thumbnail_location, " + "image.info_location, " + "image.detail_location, "
      + "prod.register_date " + "FROM product AS prod " + "JOIN product_image AS image "
      + "ON prod.image_id = image.id " + "JOIN company AS comp " + "ON prod.company_id=comp.id "
      + "WHERE `prod.id` = ?";
  public static final String SELECT_ALL_PRODUCT = "SELECT " + "prod.id, "
      + "comp.id AS `company_id`, " + "comp.company_name, " + "comp.manager_name, "
      + "comp.contact, " + "comp.addr, " + "prod.`name`, " + "prod.category, " + "prod.price, "
      + "prod.`point`, " + "prod.discount_rate, " + "prod.delivery_fee, " + "prod.stock, "
      + "image.id AS `image_id`, " + "image.thumbnail_location, " + "image.info_location, "
      + "image.detail_location, " + "prod.register_date " + "FROM product AS prod "
      + "JOIN product_image AS image " + "ON prod.image_id = image.id " + "JOIN company AS comp "
      + "ON prod.company_id=comp.id;";
  public static final String UPDATE_PRODUCT =
      "UPDATE `product` SET `company_id`=?, `name`=?, `category`=?, `price`=?, `point`=?, `discout_rate`=?, `delivery_fee`=?, `stock`=?, `image_id`=? WHERE `id`=?;";
  public static final String DELETE_PRODUCT = "DELETE FROM `product` WHERE `id`=?";
  public static final String COUNT_PRODUCT = "SELECT COUNT(*) FROM `product`;";


  public static final String INSERT_PROD_IMAGE =
      "INSERT INTO `product_image` (`thumbnail_location`, `info_location`, `detail_location`) VALUES (?,?,?)";
  public static final String SELECT_PROD_IMAGE = "SELECT * FROM `product_image` WHERE `id` = ?";

  public static final String INSERT_COMPANY =
      "INSERT INTO `company` (`company_name`, `manager_name`, `contact`, `addr`) VALUES (?,?,?,?);";

  private Query() {
    // Empty constructor
  }
}
