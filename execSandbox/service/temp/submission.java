#include <iostream>

int main() {
    int rows;

    // Get the number of rows for the pattern
    std::cout << "Enter the number of rows for the star pattern: ";
    std::cin >> rows;

    for (int i = 1; i <= rows; ++i) {
        for (int j = 1; j <= i; ++j) {
            std::cout << "* ";
        }
        std::cout << std::endl;
    }

    return 0;
}
