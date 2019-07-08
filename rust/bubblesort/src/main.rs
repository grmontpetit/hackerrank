fn main() {
    let i = read_size();
    let array = read_array();
    let values = bubble_sort(array);
    print_results(values);
}

fn print_results(r: (i32, i32, i32, Vec<i32>)) -> () {
    println!("Array is sorted in {} swaps.", r.0);
    println!("First Element: {}", r.1);
    println!("Last Element: {}", r.2);
}

fn int_extract(opt: Option<&i32>) -> i32 {
    match opt {
        Some(i) => return *i,
        None => return 1,
    }
}

fn bubble_sort(vec: Vec<i32>) -> (i32, i32, i32, Vec<i32>) {
    let mut sorted = vec;
    let mut has_swapped = false;
    let mut nb_swaps = 0;
    let mut nb_iterations = 0;
    let mut i = 0;
    while i <= sorted.len() - 1 {
        nb_iterations = nb_iterations + 1;
        if i == sorted.len() - 1 {
            if !has_swapped {
                return (nb_swaps, int_extract(sorted.first()), int_extract(sorted.last()), sorted);
            } else {
                i = 0;
                has_swapped = false;
            }
        } else {
            let current = sorted[i];
            let next = sorted[i + 1];
            if current > next {
                sorted[i] = next;
                sorted[i + 1] = current;
                has_swapped = true;
                nb_swaps = nb_swaps + 1;
            }
            i = i + 1;
        }
    }
    return (nb_swaps, int_extract(sorted.first()), int_extract(sorted.last()), sorted);
}

fn read_size() -> i32 {
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

fn read_array() -> Vec<i32> {
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
    return s.split(" ").into_iter().map(|x| {x.parse().unwrap()}).collect();
}
