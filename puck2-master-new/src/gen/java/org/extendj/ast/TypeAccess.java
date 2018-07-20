/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.0 */
package org.extendj.ast;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Set;
import beaver.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.LinkedHashSet;
import org.jastadd.util.*;
import java.util.zip.*;
import java.io.*;
import org.jastadd.util.PrettyPrintable;
import org.jastadd.util.PrettyPrinter;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
/**
 * @ast node
 * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\grammar\\Java.ast:92
 * @astdecl TypeAccess : Access ::= <Package:String> <ID:String>;
 * @production TypeAccess : {@link Access} ::= <span class="component">&lt;Package:String&gt;</span> <span class="component">&lt;ID:String&gt;</span>;

 */
public class TypeAccess extends Access implements Cloneable {
  /**
   * @aspect Java4PrettyPrint
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\PrettyPrint.jadd:608
   */
  public void prettyPrint(PrettyPrinter out) {
    if (hasPackage()) {
      out.print(getPackage());
      out.print(".");
    }
    out.print(getID());
  }
  /**
   * @aspect NodeConstructors
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\NodeConstructors.jrag:46
   */
  public TypeAccess(String name, int start, int end) {
    this(name);
    this.start = this.IDstart = start;
    this.end = this.IDend = end;
  }
  /**
   * @aspect NodeConstructors
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\NodeConstructors.jrag:58
   */
  public TypeAccess(String typeName) {
    this("", typeName);
  }
  /**
   * @aspect PrettyPrintUtil
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\PrettyPrintUtil.jrag:67
   */
  @Override public String toString() {
    if (getPackage().isEmpty()) {
      return getID();
    } else {
      return getPackage() + "." + getID();
    }
  }
  /** This method assumes that the bound type is generic. 
   * @aspect GenericsTypeAnalysis
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:417
   */
  public boolean isRaw() {
    ASTNode parent = getParent();
    while (parent instanceof Dot) {
      parent = parent.getParent();
    }
    if (parent instanceof ParTypeAccess) {
      return false;
    }
    if (parent instanceof ImportDecl) {
      return false;
    }
    return true;
  }
  /**
   * @aspect Diamond
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java7\\frontend\\Diamond.jrag:268
   */
  @Override
  public Access substituted(Collection<TypeVariable> original,
      List<TypeVariable> substitution) {
    TypeDecl decl = decl();
    int i = 0;
    for (TypeVariable typeVar : original) {
      if (typeVar == decl) {
        return new TypeAccess(substitution.getChild(i).getID());
      }
      i += 1;
    }
    return super.substituted(original, substitution);
  }
  /**
   * @aspect FunctionalInterface
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\FunctionalInterface.jrag:186
   */
  public boolean sameType(TypeAccess t) {
    // First, two type variables that are to be compared are checked to see if
    // they are both declared by methods and that one of the methods is not
    // inside the scope of the other method. If this is the case it is enough
    // to simply check that the positions are equal.  Otherwise the types has
    // to equal.

    if (type() instanceof TypeVariable && t.type() instanceof TypeVariable) {
      TypeVariable t1 = (TypeVariable) type();
      TypeVariable t2 = (TypeVariable) t.type();
      if (t1.typeVarInMethod() && t2.typeVarInMethod()
          && t1.genericMethodLevel() == t2.genericMethodLevel()) {
        return t1.typeVarPosition() == t2.typeVarPosition();
      } else {
        if (t1.enclosingType() == t2.enclosingType()) {
          return t1 == t2;
        } else {
          return t1.sameType(t2);
        }
      }
    } else if (type() instanceof TypeVariable && !(t.type() instanceof TypeVariable)) {
      TypeVariable t1 = (TypeVariable) type();
      TypeDecl t2 = t.type().erasure();
      for (Access bound : t1.getTypeBoundList()) {
        if (bound.type() != t2) {
          return false;
        }
      }
      return true;
    } else if (t.type() instanceof TypeVariable && !(type() instanceof TypeVariable)) {
      TypeDecl t1 = type().erasure();
      TypeVariable t2 = (TypeVariable) t.type();
      for (Access bound : t2.getTypeBoundList()) {
        if (bound.type() != t1) {
          return false;
        }
      }
      return true;
    } else if (type() == t.type()) {
      return true;
    } else {
      return false;
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public TypeAccess() {
    super();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:10
   */
  public void init$Children() {
  }
  /**
   * @declaredat ASTNode:12
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Package", "ID"},
    type = {"String", "String"},
    kind = {"Token", "Token"}
  )
  public TypeAccess(String p0, String p1) {
    setPackage(p0);
    setID(p1);
  }
  /**
   * @declaredat ASTNode:21
   */
  public TypeAccess(beaver.Symbol p0, beaver.Symbol p1) {
    setPackage(p0);
    setID(p1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:26
   */
  protected int numChildren() {
    return 0;
  }
  /**
   * @apilevel internal
   * @declaredat ASTNode:32
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:36
   */
  public void flushAttrCache() {
    super.flushAttrCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:44
   */
  public TypeAccess clone() throws CloneNotSupportedException {
    TypeAccess node = (TypeAccess) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:49
   */
  public TypeAccess copy() {
    try {
      TypeAccess node = (TypeAccess) clone();
      node.parent = null;
      if (children != null) {
        node.children = (ASTNode[]) children.clone();
      }
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:68
   */
  @Deprecated
  public TypeAccess fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:78
   */
  public TypeAccess treeCopyNoTransform() {
    TypeAccess tree = (TypeAccess) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) children[i];
        if (child != null) {
          child = child.treeCopyNoTransform();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:98
   */
  public TypeAccess treeCopy() {
    TypeAccess tree = (TypeAccess) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) getChild(i);
        if (child != null) {
          child = child.treeCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:112
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node) && (tokenString_Package == ((TypeAccess) node).tokenString_Package) && (tokenString_ID == ((TypeAccess) node).tokenString_ID);    
  }
  /**
   * Replaces the lexeme Package.
   * @param value The new value for the lexeme Package.
   * @apilevel high-level
   */
  public void setPackage(String value) {
    tokenString_Package = value;
  }
  /** @apilevel internal 
   */
  protected String tokenString_Package;
  /**
   */
  public int Packagestart;
  /**
   */
  public int Packageend;
  /**
   * JastAdd-internal setter for lexeme Package using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme Package
   * @apilevel internal
   */
  public void setPackage(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
    throw new UnsupportedOperationException("setPackage is only valid for String lexemes");
    tokenString_Package = (String)symbol.value;
    Packagestart = symbol.getStart();
    Packageend = symbol.getEnd();
  }
  /**
   * Retrieves the value for the lexeme Package.
   * @return The value for the lexeme Package.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="Package")
  public String getPackage() {
    return tokenString_Package != null ? tokenString_Package : "";
  }
  /**
   * Replaces the lexeme ID.
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /** @apilevel internal 
   */
  protected String tokenString_ID;
  /**
   */
  public int IDstart;
  /**
   */
  public int IDend;
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   * @param symbol Symbol containing the new value for the lexeme ID
   * @apilevel internal
   */
  public void setID(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
    throw new UnsupportedOperationException("setID is only valid for String lexemes");
    tokenString_ID = (String)symbol.value;
    IDstart = symbol.getStart();
    IDend = symbol.getEnd();
  }
  /**
   * Retrieves the value for the lexeme ID.
   * @return The value for the lexeme ID.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Token(name="ID")
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * @aspect TypeScopePropagation
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\LookupType.jrag:361
   */
  private TypeDecl refined_TypeScopePropagation_TypeAccess_decl()
{
    SimpleSet<TypeDecl> decls = decls();
    if (decls.isSingleton()) {
      return decls.singletonValue();
    }
    return unknownType();
  }
  /**
   * @attribute syn
   * @aspect AccessControl
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\AccessControl.jrag:158
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessControl", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\AccessControl.jrag:158")
  public Collection<Problem> accessControlProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        TypeDecl hostType = hostType();
        if (hostType != null && !hostType.isUnknown() && !type().accessibleFrom(hostType)) {
          problems.add(errorf("%s in %s can not access type %s",
              this.prettyPrint(), hostType().fullName(), type().fullName()));
        } else if ((hostType == null || hostType.isUnknown())
            && !type().accessibleFromPackage(hostPackage())) {
          problems.add(errorf("%s can not access type %s", this.prettyPrint(), type().fullName()));
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\LookupType.jrag:332
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\LookupType.jrag:332")
  public SimpleSet<TypeDecl> decls() {
    SimpleSet<TypeDecl> decls_value = packageName().equals("")
          ? lookupType(name())
          : toSet(lookupType(packageName(), name()));
    return decls_value;
  }
  /**
   * @attribute syn
   * @aspect TypeScopePropagation
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\LookupType.jrag:361
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeScopePropagation", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\LookupType.jrag:361")
  public TypeDecl decl() {
    {
        TypeDecl decl = refined_TypeScopePropagation_TypeAccess_decl();
        if (decl instanceof GenericTypeDecl && isRaw()) {
          return ((GenericTypeDecl) decl).lookupParTypeDecl(Collections.<TypeDecl>emptyList());
        }
        return decl;
      }
  }
  /**
   * @attribute syn
   * @aspect VariableScope
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\LookupVariable.jrag:264
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="VariableScope", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\LookupVariable.jrag:264")
  public SimpleSet<Variable> qualifiedLookupVariable(String name) {
    {
        if (type().accessibleFrom(hostType())) {
          SimpleSet<Variable> c = type().memberFields(name);
          c = keepAccessibleFields(c);
          if (type().isClassDecl() && c.isSingleton()) {
            c = removeInstanceVariables(c);
          }
          return c;
        }
        return emptySet();
      }
  }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\NameCheck.jrag:234
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\NameCheck.jrag:234")
  public Collection<Problem> nameProblems() {
    {
        if (isQualified() && !qualifier().isTypeAccess() && !qualifier().isPackageAccess()) {
          return Collections.singletonList(
              errorf("the type %s is not accessible in this context", decl().typeName()));
        }
        // Type lookup for an unknown type can result in either an empty set or
        // a set containing the unknown type.
        // The empty set is returned for an unqualified type lookup, and the
        // unknown type is returned for qualified type lookup.
        // TODO(joqvist): Make qualified and unqualified type lookup behave the same on failure.
        SimpleSet<TypeDecl> decls = decls();
        if (decls.isSingleton()) {
          if (decls.singletonValue().isUnknown()) {
            return Collections.singletonList(
                errorf("no visible type named %s", typeName()));
          } else {
            return Collections.emptyList();
          }
        } else if (decls.isEmpty()) {
          return Collections.singletonList(
              errorf("no visible type named %s", typeName()));
        } else {
          StringBuilder sb = new StringBuilder();
          sb.append("multiple types named " + name() + " are visible:");
          for (TypeDecl type : decls) {
            sb.append(" " + type.typeName());
          }
          return Collections.singletonList(error(sb.toString()));
        }
      }
  }
  /**
   * Has package name (not @primitive)
   * @attribute syn
   * @aspect PrettyPrintUtil
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\PrettyPrintUtil.jrag:284
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PrettyPrintUtil", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\PrettyPrintUtil.jrag:284")
  public boolean hasPackage() {
    boolean hasPackage_value = !getPackage().isEmpty() && decl().isReferenceType();
    return hasPackage_value;
  }
  /**
   * @attribute syn
   * @aspect Names
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\QualifiedNames.jrag:39
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Names", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\QualifiedNames.jrag:39")
  public String name() {
    String name_value = getID();
    return name_value;
  }
  /**
   * @attribute syn
   * @aspect Names
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\QualifiedNames.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Names", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\QualifiedNames.jrag:43")
  public String packageName() {
    String packageName_value = getPackage();
    return packageName_value;
  }
  /** @return the qualified type name including the package name. 
   * @attribute syn
   * @aspect Names
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\QualifiedNames.jrag:57
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Names", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\QualifiedNames.jrag:57")
  public String nameWithPackage() {
    String nameWithPackage_value = getPackage().equals("") ? name() : (getPackage() + "." + name());
    return nameWithPackage_value;
  }
  /**
   * @attribute syn
   * @aspect Names
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\QualifiedNames.jrag:73
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="Names", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\QualifiedNames.jrag:73")
  public String typeName() {
    String typeName_value = isQualified() ? (qualifier().typeName() + "." + name()) : nameWithPackage();
    return typeName_value;
  }
  /**
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\ResolveAmbiguousNames.jrag:35
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessTypes", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\ResolveAmbiguousNames.jrag:35")
  public boolean isTypeAccess() {
    boolean isTypeAccess_value = true;
    return isTypeAccess_value;
  }
  /**
   * Tests if this is a qualified type access expression for
   * the given type.
   * @attribute syn
   * @aspect AccessTypes
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\ResolveAmbiguousNames.jrag:43
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="AccessTypes", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\ResolveAmbiguousNames.jrag:43")
  public boolean isTypeAccess(String packageName, String type) {
    boolean isTypeAccess_String_String_value = getID().equals(type) && getPackage().equals(packageName());
    return isTypeAccess_String_String_value;
  }
  /**
   * Defines the expected kind of name for the left hand side in a qualified
   * expression.
   * @attribute syn
   * @aspect SyntacticClassification
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\SyntacticClassification.jrag:60
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="SyntacticClassification", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\SyntacticClassification.jrag:60")
  public NameType predNameType() {
    NameType predNameType_value = NameType.PACKAGE_OR_TYPE_NAME;
    return predNameType_value;
  }
  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\TypeAnalysis.jrag:298
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\TypeAnalysis.jrag:298")
  public TypeDecl type() {
    TypeDecl type_value = decl();
    return type_value;
  }
  /**
   * @attribute syn
   * @aspect TypeHierarchyCheck
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\TypeHierarchyCheck.jrag:224
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeHierarchyCheck", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\TypeHierarchyCheck.jrag:224")
  public boolean staticContextQualifier() {
    boolean staticContextQualifier_value = true;
    return staticContextQualifier_value;
  }
  /**
   * @attribute syn
   * @aspect LookupParTypeDecl
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:1302
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LookupParTypeDecl", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:1302")
  public boolean usesTypeVariable() {
    boolean usesTypeVariable_value = decl().usesTypeVariable() || super.usesTypeVariable();
    return usesTypeVariable_value;
  }
  /**
   * @attribute syn
   * @aspect LambdaParametersInference
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\TypeCheck.jrag:620
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="LambdaParametersInference", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\TypeCheck.jrag:620")
  public boolean mentionsTypeVariable(TypeVariable var) {
    boolean mentionsTypeVariable_TypeVariable_value = getPackage().isEmpty() && getID().equals(var.getID());
    return mentionsTypeVariable_TypeVariable_value;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
  /** @apilevel internal */
  public boolean canRewrite() {
    return false;
  }
  /** @apilevel internal */
  protected void collect_contributors_CompilationUnit_problems(CompilationUnit _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\AccessControl.jrag:156
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\NameCheck.jrag:232
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Annotations.jrag:488
    if (decl().isDeprecated()
              && !withinDeprecatedAnnotation()
              && (hostType() == null || hostType().topLevelType() != decl().topLevelType())
              && !withinSuppressWarnings("deprecation")) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:679
    if (type().isRawType() && type().isNestedType()
              && type().enclosingType().isParameterizedType()
              && !type().enclosingType().isRawType()) {
      {
        java.util.Set<ASTNode> contributors = _map.get(_root);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) _root, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : accessControlProblems()) {
      collection.add(value);
    }
    for (Problem value : nameProblems()) {
      collection.add(value);
    }
    if (decl().isDeprecated()
              && !withinDeprecatedAnnotation()
              && (hostType() == null || hostType().topLevelType() != decl().topLevelType())
              && !withinSuppressWarnings("deprecation")) {
      collection.add(warning(decl().typeName() + " has been deprecated"));
    }
    if (type().isRawType() && type().isNestedType()
              && type().enclosingType().isParameterizedType()
              && !type().enclosingType().isRawType()) {
      collection.add(error("Can not access a member type of a paramterized type as a raw type"));
    }
  }
}
