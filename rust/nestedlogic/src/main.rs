fn main() {
    let mut borrow_tuple = (0, 0, 0);
    let mut return_tuple = (0, 0, 0);
    let return_date = read_date_line();
    match return_date {
        Ok(date) => {
            return_tuple = (date[0], date[1], date[2]);
        },
        Err(message) => println!("Error: {}", message),
    }
    let borrow_date = read_date_line();
    match borrow_date {
        Ok(date) => {
            borrow_tuple = (date[0], date[1], date[2]);
        },
        Err(message) => println!("Error: {}", message),
    }
    println!("{}", check_fine(return_tuple, borrow_tuple))
}

// day month year
fn check_fine(return_date: (i32, i32, i32), borrow_date: (i32, i32, i32)) -> i32 {
    let year_diff = return_date.2 - borrow_date.2;
    let month_diff = return_date.1 - borrow_date.1;
    let day_diff = return_date.0 - borrow_date.0;
    if year_diff == 0 && month_diff == 0 && day_diff > 0 {
        return day_diff * 15;
    }
    if year_diff == 0 && month_diff > 0 {
        return month_diff * 500;
    }
    if year_diff > 0 {
        return 10000;
    }
    return 0;
}

// day month year
fn read_date_line() -> Result<Vec<i32>, &'static str> {
    use std::io::{stdin,stdout,Write};
    let mut s = String::new();
    let _ = stdout().flush();
    match stdin().read_line(&mut s) {
        Ok(_) => {
            if let Some('\n') = s.chars().next_back() {
                s.pop();
            }
            if let Some('\r') = s.chars().next_back() {
                s.pop();
            }
            return std::result::Result::Ok(s.split(" ").into_iter().map(|x| {x.parse().unwrap()}).collect());
        },
        Err(_) => return std::result::Result::Err("Error reading date."),
    }
}
