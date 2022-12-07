# TCP SYN Scanner

This program is study project. It`s view how to use multithreading and MVC pattern.

In root of it used Socket class for trying to connect to host port by ICMP protocol.

You can use next key:

- `-h` for included list of IPv4.
    - For example:  `-h 192.168.1.1, 10.0.8.1` or range `-h 10.8.0.1-10, 172.16.1.1`
- `-p` for included list of ports.
    - For example: `-p 22,80,443` or range `-p 20-22,443,5000-5001`
- `-t` for entering count of threads.
    - For example: `-t 10`

Example:

```bash
Type to request for scanning:
scan -h 95.165.154.50,8.8.8.8-10 -p 80,443-444,5000-5003
Opened:
 95.165.154.50:5000
 95.165.154.50:5001
 95.165.154.50:443
 8.8.8.8:443
```
