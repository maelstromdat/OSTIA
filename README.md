# OSTIA
OSTIA is a parser to elicit and represent Storm Topologies by reverse engineering of Storm-based Big-Data programs. The intent and aim of OSTIA is to automatically represent models of Storm topologies and conduct inference analysis on them before nasty issues at run-time take place. A part of said run-time issues can be avoided by using OSTIA and the analyses intended within it.

## Usage

first make sure you have installed the depedencies:

```bash
$ gem install trollop
$ gem install awesome_print
```

Then run OSTIA with `dot` format as output:

```bash
$ cd ostia-rb
$ ./ostia.rb --topology ../examples/FocusedCrawler.java --output focused_crawler.dot --format dot
```
or in `json` format:

```bash
$ cd ostia-rb
$ ./ostia.rb --topology ../examples/FocusedCrawler.java --output focused_crawler.json --format json
```


## Contact

If you notice a bug, want to request a feature, or have a question or feedback, please send us an email. We would like to hear from people using our code.

## Licence

The code is published under the FreeBSD License.
