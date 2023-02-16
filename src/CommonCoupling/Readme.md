# Common Coupling

## Introduction

## Problem

    public class Logger {
        private static File logFile;
    
        public static void log(String message) {
            try {
                FileWriter writer = new FileWriter(logFile, true);
                writer.write(message + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public class ShoppingCart {
        private List<Item> items;
    
        public void addItem(Item item) {
            items.add(item);
            Logger.log("Added item " + item.getName() + " to shopping cart.");
        }
    
        public void removeItem(Item item) {
            items.remove(item);
            Logger.log("Removed item " + item.getName() + " from shopping cart.");
        }
    
        public double calculateTotal() {
            double total = 0.0;
            for (Item item : items) {
                total += item.getPrice();
            }
            Logger.log("Calculated total price of items in shopping cart.");
            return total;
        }

    }

    public class Item {
        private String name;
        private double price;
    
        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    
        public String getName() {
            return name;
        }
    
        public double getPrice() {
            return price;
        }
    }


Trong ví dụ này, lớp `Logger` có một trường tĩnh logFile được chia sẻ trên toàn bộ hệ thống. 
Lớp `ShoppingCart` phụ thuộc vào lớp `Logger` để ghi lại các tin nhắn về việc thêm và xóa các mặt hàng khỏi giỏ hàng, cũng như tính tổng giá tiền. 
Điều này tạo ra kết kết nối chung, vì lớp `ShoppingCart` phụ thuộc vào một tài nguyên được chia sẻ được sử dụng bởi các phần khác của hệ thống.

## Solution
Để giảm kết kết nối chung trong ví dụ trên, chúng ta có thể sử dụng Dependency Injection (DI) để giảm sự phụ thuộc của lớp `ShoppingCart` vào lớp `Logger`. Chúng ta có thể tạo ra một giao diện `LoggerInterface` và một lớp `FileLogger` để đóng gói tài nguyên logFile trong một module riêng.

    public interface LoggerInterface {
        void log(String message);
    }
    
    public class FileLogger implements LoggerInterface {
        private File logFile;
    
        public FileLogger(File logFile) {
            this.logFile = logFile;
        }
    
        public void log(String message) {
            try {
                FileWriter writer = new FileWriter(logFile, true);
                writer.write(message + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public class ShoppingCart {
        private List<Item> items;
        private LoggerInterface logger;
    
        public ShoppingCart(LoggerInterface logger) {
            this.items = new ArrayList<>();
            this.logger = logger;
        }
    
        public void addItem(Item item) {
            items.add(item);
            logger.log("Added item " + item.getName() + " to shopping cart.");
        }
    
        public void removeItem(Item item) {
            items.remove(item);
            logger.log("Removed item " + item.getName() + " from shopping cart.");
        }
    
        public double calculateTotal() {
            double total = 0.0;
            for (Item item : items) {
                total += item.getPrice();
            }
            logger.log("Calculated total price of items in shopping cart.");
            return total;
        }
    }

    public class Item {
        private String name;
        private double price;
    
        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }
    
        public String getName() {
            return name;
        }
    
        public double getPrice() {
            return price;
        }
    }

Trong ví dụ này, chúng ta đã tạo ra một interface `LoggerInterface` để định nghĩa phương thức log() và một lớp `FileLogger` để triển khai phương thức đó. Lớp `FileLogger` có một trường logFile để lưu trữ tập tin nhật ký.

Lớp `ShoppingCart` được sửa đổi để chấp nhận một đối tượng `LoggerInterface` trong hàm tạo. Khi thêm hoặc xóa một mặt hàng khỏi giỏ hàng, lớp `ShoppingCart` gọi phương thức log() của đối tượng `LoggerInterface` để ghi lại hoạt động đó.

Khi tạo đối tượng `ShoppingCart`, chúng ta có thể truyền một đối tượng `FileLogger` vào đó, và truyền tập tin nhật ký cho đối tượng `FileLogger`. Điều này đóng gói tài nguyên logFile vào một module riêng và cho phép lớp `ShoppingCart` tương tác với nó thông qua giao diện `LoggerInterface` mà không cần biết về triển khai của nó.
