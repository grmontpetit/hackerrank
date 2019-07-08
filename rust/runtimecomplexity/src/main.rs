const PRIME: &'static str = "Prime";
const NOT_PRIME: &'static str = "Not prime";

// 10 numbers:
// 1000000000
// 1000000001
// 1000000002
// 1000000003
// 1000000004
// 1000000005
// 1000000006
// 1000000007
// 1000000008
// 1000000009
// Not prime
// Not prime
// Not prime
// Not prime
// Not prime
// Not prime
// Not prime
// Prime
// Not prime
// Prime
fn main() {
    let size = read_int();
    let mut vector = Vec::new();
    for _x in 0..size {
        vector.push(read_int());
    }
    for i in 0..vector.len() {
        //is_prime_print(vector[i]);
        is_prime_print_optimized(vector[i]);
    }
}

fn is_prime_print_optimized(number: i32) -> () {
    if number < 0 || number == 0 || number == 1 || number == 4 {
        println!("{}", NOT_PRIME);
        return;
    }
    let n = number as f64;
    let max = n.sqrt() as i32;
    for i in 2..max + 1 {
        if number % i == 0 {
            println!("{}", NOT_PRIME);
            return;
        }
    }
    println!("{}", PRIME);
}

fn is_prime_print(number: i32) -> () {
    if number == 1 {
        println!("{}", NOT_PRIME);
        return;
    }
    for i in 2..number - 1 {
        if number % i == 0 {
            println!("{}", NOT_PRIME);
            return;
        }
    }
    println!("{}", PRIME);
}

fn read_int() -> i32 {
    use std::io::{stdin,stdout,Write};
    let mut s = String::new();
    let _ = stdout().flush();
    stdin().read_line(&mut s).expect("Did not enter a correct string");
    if let Some('\n')=s.chars().next_back() {
        s.pop();
    }
    if let Some('\r')=s.chars().next_back() {
        s.pop();
    }
    return s.parse().unwrap();
}