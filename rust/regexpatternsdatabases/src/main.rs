extern crate regex;
use regex::Regex;

struct ContactLine {
    name: String,
    email: String
}

fn main() {
    let size = read_int();
    let mut contact_vector: Vec<ContactLine> = Vec::new();
    for _x in 0..size {
        match read_contact() {
            Some(contact) => {
                contact_vector.push(contact);
            },
            None => (),
        }
    }
    let mut gmails: Vec<&ContactLine> = contact_vector.iter().filter(|contact| contact.email.contains("gmail.com")).collect();
    gmails.sort_by_key(|x| &x.name);
    for gmail in gmails {
        println!("{}", gmail.name);
    }
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

fn read_contact() -> Option<ContactLine> {
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
            return unmarshall(&s);
        },
        Err(_) => return None
    }
}


fn unmarshall(line: &str) -> Option<ContactLine> {

    let email_regex = Regex::new(r"(\w*)@gmail.com").unwrap();
    let email_match = email_regex.find(line);
    let name_regex = Regex::new(r"(\w*)( )").unwrap();
    let name_match = name_regex.find(line).unwrap();

    match email_match {
        Some(email) => {
                return Some(ContactLine{name: String::from(name_match.as_str().trim()),
                                   email: String::from(email.as_str())})},
        None => return None,
    }

}