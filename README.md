# DemoWebShop Automation Testing 🛍️🤖

## Project Overview 📊

This project is an automation testing suite for the DemoWebShop application. The goal of this project is to validate the functionality and user experience of the DemoWebShop site using automated tests. It covers key user interactions, including login, product search, checkout, and more.

## Tech Stack 🧑‍💻⚙️

- **Testing Framework**: [Selenium WebDriver](https://www.selenium.dev/) 🖥️
- **Programming Language**: [Java](https://www.oracle.com/java/) ☕
- **Build Tool**: [Maven](https://maven.apache.org/) 🛠️
- **Test Runner**: [TestNG](https://testng.org/) ✅
- **Reporting**: [Allure](https://allure.qatools.ru/) 📑
- **Version Control**: [Git](https://git-scm.com/) 🔄

## Setup and Installation 📝

### Prerequisites ⚡

Ensure you have the following installed:

1. [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) ☕
2. [Maven](https://maven.apache.org/install.html) 🛠️
3. [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git) 🧑‍💻
4. [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/) (For running tests on Chrome) 🖥️

### Cloning the Repository 🔁

Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/DemoWebShop-Automation-Testing.git
cd DemoWebShop-Automation-Testing
```

### Install Dependencies 📦

Navigate to the project folder and install the necessary dependencies:

```bash
mvn clean install
```

### Running the Tests 🚦

To run the tests, use the following Maven command:

```bash
mvn test
```

### Generating Test Reports 📈

You can generate the Allure test report by running:

```bash
mvn allure:serve
```

This will open the Allure report in your default browser.

## Features ✨

- **Login Tests**: Verifying user login functionality 🔑
- **Register Tests**: Validating user registration process and form validation 📝
- **Search Functionality Tests**: Validating product search on the platform 🔍
- **Add to Cart Tests**: Verifying the functionality of adding products to the shopping cart 🛒
- **Checkout Flow Tests**: Ensuring smooth product checkout process 💳
- **Order Placement Tests**: Ensuring that orders can be placed successfully and confirmation is shown 📦
- **Order Tracking Tests**: Verifying order tracking functionality after an order has been placed 🚚
- **Cross-browser Testing**: Run tests across different browsers like Chrome, Firefox, and Edge 🌐
- **Data-Driven Tests**: Supporting different test data inputs via Excel or CSV files 🗂️

## Contributing 🤝

We welcome contributions! If you'd like to contribute to this project, please fork the repository and submit a pull request. Here's how you can get started:

1. Fork the repository 🍴
2. Create a new branch (`git checkout -b feature/your-feature`) 🌱
3. Commit your changes (`git commit -am 'Add new feature'`) ✍️
4. Push to your branch (`git push origin feature/your-feature`) ⬆️
5. Create a new Pull Request 📥

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact 📧

For any questions or support, please contact:

- **Email**: mohammed.hasan.abdelfattah@gmail.com📬
