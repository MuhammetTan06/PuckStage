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
 * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\grammar\\EnhancedFor.ast:1
 * @astdecl EnhancedForStmt : BranchTargetStmt ::= Modifiers TypeAccess:Access VariableDecl:VariableDeclarator Expr Stmt;
 * @production EnhancedForStmt : {@link BranchTargetStmt} ::= <span class="component">{@link Modifiers}</span> <span class="component">TypeAccess:{@link Access}</span> <span class="component">VariableDecl:{@link VariableDeclarator}</span> <span class="component">{@link Expr}</span> <span class="component">{@link Stmt}</span>;

 */
public class EnhancedForStmt extends BranchTargetStmt implements Cloneable, VariableScope {
  /**
   * @aspect EnhancedFor
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:158
   */
  public void prettyPrint(PrettyPrinter out) {
    out.print("for (");
    out.print(getModifiers());
    out.print(getTypeAccess());
    out.print(" " + getVariableDecl().name());
    out.print(" : ");
    out.print(getExpr());
    out.print(") ");
    if (getStmt() instanceof Block) {
      out.print(getStmt());
    } else {
      out.print("{");
      out.println();
      out.indent(1);
      out.print(getStmt());
      out.println();
      out.print("}");
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public EnhancedForStmt() {
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
    children = new ASTNode[5];
  }
  /**
   * @declaredat ASTNode:13
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Modifiers", "TypeAccess", "VariableDecl", "Expr", "Stmt"},
    type = {"Modifiers", "Access", "VariableDeclarator", "Expr", "Stmt"},
    kind = {"Child", "Child", "Child", "Child", "Child"}
  )
  public EnhancedForStmt(Modifiers p0, Access p1, VariableDeclarator p2, Expr p3, Stmt p4) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
    setChild(p3, 3);
    setChild(p4, 4);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:26
   */
  protected int numChildren() {
    return 5;
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
    canCompleteNormally_reset();
    assignedAfter_Variable_reset();
    unassignedAfter_Variable_reset();
    iterableTypeAccess_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:44
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:48
   */
  public EnhancedForStmt clone() throws CloneNotSupportedException {
    EnhancedForStmt node = (EnhancedForStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:53
   */
  public EnhancedForStmt copy() {
    try {
      EnhancedForStmt node = (EnhancedForStmt) clone();
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
   * @declaredat ASTNode:72
   */
  @Deprecated
  public EnhancedForStmt fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:82
   */
  public EnhancedForStmt treeCopyNoTransform() {
    EnhancedForStmt tree = (EnhancedForStmt) copy();
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
   * @declaredat ASTNode:102
   */
  public EnhancedForStmt treeCopy() {
    EnhancedForStmt tree = (EnhancedForStmt) copy();
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
   * @declaredat ASTNode:116
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Modifiers child.
   * @param node The new node to replace the Modifiers child.
   * @apilevel high-level
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Modifiers child.
   * @return The current node used as the Modifiers child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Modifiers")
  public Modifiers getModifiers() {
    return (Modifiers) getChild(0);
  }
  /**
   * Retrieves the Modifiers child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Modifiers child.
   * @apilevel low-level
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers) getChildNoTransform(0);
  }
  /**
   * Replaces the TypeAccess child.
   * @param node The new node to replace the TypeAccess child.
   * @apilevel high-level
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the TypeAccess child.
   * @return The current node used as the TypeAccess child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="TypeAccess")
  public Access getTypeAccess() {
    return (Access) getChild(1);
  }
  /**
   * Retrieves the TypeAccess child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the TypeAccess child.
   * @apilevel low-level
   */
  public Access getTypeAccessNoTransform() {
    return (Access) getChildNoTransform(1);
  }
  /**
   * Replaces the VariableDecl child.
   * @param node The new node to replace the VariableDecl child.
   * @apilevel high-level
   */
  public void setVariableDecl(VariableDeclarator node) {
    setChild(node, 2);
  }
  /**
   * Retrieves the VariableDecl child.
   * @return The current node used as the VariableDecl child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="VariableDecl")
  public VariableDeclarator getVariableDecl() {
    return (VariableDeclarator) getChild(2);
  }
  /**
   * Retrieves the VariableDecl child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the VariableDecl child.
   * @apilevel low-level
   */
  public VariableDeclarator getVariableDeclNoTransform() {
    return (VariableDeclarator) getChildNoTransform(2);
  }
  /**
   * Replaces the Expr child.
   * @param node The new node to replace the Expr child.
   * @apilevel high-level
   */
  public void setExpr(Expr node) {
    setChild(node, 3);
  }
  /**
   * Retrieves the Expr child.
   * @return The current node used as the Expr child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Expr")
  public Expr getExpr() {
    return (Expr) getChild(3);
  }
  /**
   * Retrieves the Expr child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Expr child.
   * @apilevel low-level
   */
  public Expr getExprNoTransform() {
    return (Expr) getChildNoTransform(3);
  }
  /**
   * Replaces the Stmt child.
   * @param node The new node to replace the Stmt child.
   * @apilevel high-level
   */
  public void setStmt(Stmt node) {
    setChild(node, 4);
  }
  /**
   * Retrieves the Stmt child.
   * @return The current node used as the Stmt child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Stmt")
  public Stmt getStmt() {
    return (Stmt) getChild(4);
  }
  /**
   * Retrieves the Stmt child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Stmt child.
   * @apilevel low-level
   */
  public Stmt getStmtNoTransform() {
    return (Stmt) getChildNoTransform(4);
  }
  /**
   * @return <code>true</code> if this statement is a potential
   * branch target of the given branch statement.
   * @attribute syn
   * @aspect BranchTarget
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\BranchTarget.jrag:215
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="BranchTarget", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\BranchTarget.jrag:215")
  public boolean potentialTargetOf(Stmt branch) {
    boolean potentialTargetOf_Stmt_value = branch.canBranchTo(this);
    return potentialTargetOf_Stmt_value;
  }
  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:45
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EnhancedFor", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:45")
  public Collection<Problem> typeProblems() {
    {
        Collection<Problem> problems = new LinkedList<Problem>();
        TypeDecl exprType = getExpr().type();
        if (!exprType.isArrayDecl() && !exprType.isIterable()) {
          problems.add(errorf(
              "type %s of expression in foreach is neither array type nor java.lang.Iterable",
              exprType.name()));
        } else if (exprType.isArrayDecl()
            && !exprType.componentType()
                .assignConversionTo(getTypeAccess().type(), null)) {
          problems.add(errorf("parameter of type %s can not be assigned an element of type %s",
              getTypeAccess().type().typeName(), exprType.componentType().typeName()));
        } else if (exprType.isIterable() && !exprType.isUnknown()) {
          TypeDecl componentType = exprType.iterableElementType();
          if (!componentType.assignConversionTo(getTypeAccess().type(), null)) {
            problems.add(errorf("parameter of type %s can not be assigned an element of type %s",
                getTypeAccess().type().typeName(), componentType.typeName()));
          }
        }
        return problems;
      }
  }
  /**
   * @attribute syn
   * @aspect EnhancedFor
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:150
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="EnhancedFor", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:150")
  public SimpleSet<Variable> localLookupVariable(String name) {
    {
        if (getVariableDecl().name().equals(name)) {
          return getVariableDecl();
        }
        return lookupVariable(name);
      }
  }
  /** @apilevel internal */
  private void canCompleteNormally_reset() {
    canCompleteNormally_computed = null;
  }
  /** @apilevel internal */
  protected ASTState.Cycle canCompleteNormally_computed = null;

  /** @apilevel internal */
  protected boolean canCompleteNormally_value;

  /**
   * @attribute syn
   * @aspect UnreachableStatements
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\UnreachableStatements.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="UnreachableStatements", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\UnreachableStatements.jrag:50")
  public boolean canCompleteNormally() {
    ASTState state = state();
    if (canCompleteNormally_computed == ASTState.NON_CYCLE || canCompleteNormally_computed == state().cycle()) {
      return canCompleteNormally_value;
    }
    canCompleteNormally_value = reachable();
    if (state().inCircle()) {
      canCompleteNormally_computed = state().cycle();
    
    } else {
      canCompleteNormally_computed = ASTState.NON_CYCLE;
    
    }
    return canCompleteNormally_value;
  }
  /** @apilevel internal */
  private void assignedAfter_Variable_reset() {
    assignedAfter_Variable_values = null;
  }
  protected java.util.Map assignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteAssignment", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\DefiniteAssignment.jrag:264")
  public boolean assignedAfter(Variable v) {
    Object _parameters = v;
    if (assignedAfter_Variable_values == null) assignedAfter_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (assignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = assignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      assignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_assignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_assignedAfter_Variable_value = assignedAfter_compute(v);
        if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_assignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      assignedAfter_Variable_values.put(_parameters, new_assignedAfter_Variable_value);

      state.leaveCircle();
      return new_assignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_assignedAfter_Variable_value = assignedAfter_compute(v);
      if (((Boolean)_value.value) != new_assignedAfter_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_assignedAfter_Variable_value;
      }
      return new_assignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private boolean assignedAfter_compute(Variable v) {
      if (!getExpr().assignedAfter(v)) {
        return false;
      }
      return true;
    }
  /** @apilevel internal */
  private void unassignedAfter_Variable_reset() {
    unassignedAfter_Variable_values = null;
  }
  protected java.util.Map unassignedAfter_Variable_values;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="DefiniteUnassignment", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\DefiniteAssignment.jrag:895")
  public boolean unassignedAfter(Variable v) {
    Object _parameters = v;
    if (unassignedAfter_Variable_values == null) unassignedAfter_Variable_values = new java.util.HashMap(4);
    ASTState.CircularValue _value;
    if (unassignedAfter_Variable_values.containsKey(_parameters)) {
      Object _cache = unassignedAfter_Variable_values.get(_parameters);
      if (!(_cache instanceof ASTState.CircularValue)) {
        return (Boolean) _cache;
      } else {
        _value = (ASTState.CircularValue) _cache;
      }
    } else {
      _value = new ASTState.CircularValue();
      unassignedAfter_Variable_values.put(_parameters, _value);
      _value.value = true;
    }
    ASTState state = state();
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      boolean new_unassignedAfter_Variable_value;
      do {
        _value.cycle = state.nextCycle();
        new_unassignedAfter_Variable_value = unassignedAfter_compute(v);
        if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
          state.setChangeInCycle();
          _value.value = new_unassignedAfter_Variable_value;
        }
      } while (state.testAndClearChangeInCycle());
      unassignedAfter_Variable_values.put(_parameters, new_unassignedAfter_Variable_value);

      state.leaveCircle();
      return new_unassignedAfter_Variable_value;
    } else if (_value.cycle != state.cycle()) {
      _value.cycle = state.cycle();
      boolean new_unassignedAfter_Variable_value = unassignedAfter_compute(v);
      if (((Boolean)_value.value) != new_unassignedAfter_Variable_value) {
        state.setChangeInCycle();
        _value.value = new_unassignedAfter_Variable_value;
      }
      return new_unassignedAfter_Variable_value;
    } else {
      return (Boolean) _value.value;
    }
  }
  /** @apilevel internal */
  private boolean unassignedAfter_compute(Variable v) {
      if (!getExpr().unassignedAfter(v)) {
        return false;
      }
      for (BreakStmt stmt : targetBreaks()) {
        if (!stmt.unassignedAfterReachedFinallyBlocks(v)) {
          return false;
        }
      }
      return true;
    }
  /**
   * @attribute syn
   * @aspect NameCheck
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\NameCheck.jrag:567
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameCheck", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\NameCheck.jrag:567")
  public boolean continueLabel() {
    boolean continueLabel_value = true;
    return continueLabel_value;
  }
  /** @apilevel internal */
  private void iterableTypeAccess_reset() {
    iterableTypeAccess_computed = false;
    
    iterableTypeAccess_value = null;
  }
  /** @apilevel internal */
  protected boolean iterableTypeAccess_computed = false;

  /** @apilevel internal */
  protected Access iterableTypeAccess_value;

  /**
   * Creates a synthetic access to the type {@code java.lang.Iterable<X>},
   * where {@code X} is the loop variable type.
   * @attribute syn
   * @aspect GenericMethodsInference
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\GenericMethodsInference.jrag:81
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="GenericMethodsInference", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\GenericMethodsInference.jrag:81")
  public Access iterableTypeAccess() {
    ASTState state = state();
    if (iterableTypeAccess_computed) {
      return iterableTypeAccess_value;
    }
    state().enterLazyAttribute();
    iterableTypeAccess_value = new ParTypeAccess(
              new TypeAccess("java.lang", "Iterable"),
              new List<Access>(
                  getTypeAccess().treeCopyNoTransform()));
    iterableTypeAccess_value.setParent(this);
    iterableTypeAccess_computed = true;
    state().leaveLazyAttribute();
    return iterableTypeAccess_value;
  }
  /**
   * @attribute syn
   * @aspect PreciseRethrow
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java7\\frontend\\PreciseRethrow.jrag:78
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="PreciseRethrow", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java7\\frontend\\PreciseRethrow.jrag:78")
  public boolean modifiedInScope(Variable var) {
    boolean modifiedInScope_Variable_value = getStmt().modifiedInScope(var);
    return modifiedInScope_Variable_value;
  }
  /**
   * @attribute inh
   * @aspect EnhancedFor
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:138
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="EnhancedFor", declaredAt="C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:138")
  public SimpleSet<Variable> lookupVariable(String name) {
    SimpleSet<Variable> lookupVariable_String_value = getParent().Define_lookupVariable(this, null, name);
    return lookupVariable_String_value;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\BranchTarget.jrag:230
   * @apilevel internal
   */
  public Stmt Define_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return branch.canBranchTo(this) ? this : branchTarget(branch);
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\BranchTarget.jrag:230
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute branchTarget
   */
  protected boolean canDefine_branchTarget(ASTNode _callerNode, ASTNode _childNode, Stmt branch) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\LookupVariable.jrag:30
   * @apilevel internal
   */
  public SimpleSet<Variable> Define_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:141
      return localLookupVariable(name);
    }
    else if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:140
      return localLookupVariable(name);
    }
    else if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:139
      return localLookupVariable(name);
    }
    else {
      return getParent().Define_lookupVariable(this, _callerNode, name);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\LookupVariable.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookupVariable
   */
  protected boolean canDefine_lookupVariable(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\SyntacticClassification.jrag:36
   * @apilevel internal
   */
  public NameType Define_nameType(ASTNode _callerNode, ASTNode _childNode) {
    if (getTypeAccessNoTransform() != null && _callerNode == getTypeAccess()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:143
      return NameType.TYPE_NAME;
    }
    else {
      return getParent().Define_nameType(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\SyntacticClassification.jrag:36
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute nameType
   */
  protected boolean canDefine_nameType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\NameCheck.jrag:31
   * @apilevel internal
   */
  public VariableScope Define_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:148
      return this;
    }
    else if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:147
      return this;
    }
    else if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:146
      return this;
    }
    else {
      return getParent().Define_outerScope(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\NameCheck.jrag:31
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute outerScope
   */
  protected boolean canDefine_outerScope(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\Modifiers.jrag:434
   * @apilevel internal
   */
  public boolean Define_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    if (getModifiersNoTransform() != null && _callerNode == getModifiers()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:178
      return true;
    }
    else {
      return getParent().Define_mayBeFinal(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\Modifiers.jrag:434
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute mayBeFinal
   */
  protected boolean canDefine_mayBeFinal(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java7\\frontend\\MultiCatch.jrag:44
   * @apilevel internal
   */
  public boolean Define_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:182
      return false;
    }
    else {
      return getParent().Define_isMethodParameter(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java7\\frontend\\MultiCatch.jrag:44
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isMethodParameter
   */
  protected boolean canDefine_isMethodParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java7\\frontend\\MultiCatch.jrag:45
   * @apilevel internal
   */
  public boolean Define_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:183
      return false;
    }
    else {
      return getParent().Define_isConstructorParameter(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java7\\frontend\\MultiCatch.jrag:45
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isConstructorParameter
   */
  protected boolean canDefine_isConstructorParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java7\\frontend\\MultiCatch.jrag:46
   * @apilevel internal
   */
  public boolean Define_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:184
      return false;
    }
    else {
      return getParent().Define_isExceptionHandlerParameter(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java7\\frontend\\MultiCatch.jrag:46
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isExceptionHandlerParameter
   */
  protected boolean canDefine_isExceptionHandlerParameter(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\VariableDeclaration.jrag:133
   * @apilevel internal
   */
  public Modifiers Define_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:186
      return getModifiers();
    }
    else {
      return getParent().Define_declarationModifiers(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\VariableDeclaration.jrag:133
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declarationModifiers
   */
  protected boolean canDefine_declarationModifiers(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\VariableDeclaration.jrag:144
   * @apilevel internal
   */
  public Access Define_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:188
      return getTypeAccess();
    }
    else {
      return getParent().Define_declarationType(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\VariableDeclaration.jrag:144
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute declarationType
   */
  protected boolean canDefine_declarationType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\UnreachableStatements.jrag:49
   * @apilevel internal
   */
  public boolean Define_reachable(ASTNode _callerNode, ASTNode _childNode) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:192
      return reachable();
    }
    else {
      return getParent().Define_reachable(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\UnreachableStatements.jrag:49
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute reachable
   */
  protected boolean canDefine_reachable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\DefiniteAssignment.jrag:256
   * @apilevel internal
   */
  public boolean Define_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:205
      return getExpr().assignedAfter(v);
    }
    else if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:202
      return v == getVariableDecl() || assignedBefore(v);
    }
    else {
      return getParent().Define_assignedBefore(this, _callerNode, v);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\DefiniteAssignment.jrag:256
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignedBefore
   */
  protected boolean canDefine_assignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\DefiniteAssignment.jrag:887
   * @apilevel internal
   */
  public boolean Define_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:222
      return getExpr().unassignedAfter(v);
    }
    else if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:219
      return v != getVariableDecl() && unassignedBefore(v);
    }
    else {
      return getParent().Define_unassignedBefore(this, _callerNode, v);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\DefiniteAssignment.jrag:887
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unassignedBefore
   */
  protected boolean canDefine_unassignedBefore(ASTNode _callerNode, ASTNode _childNode, Variable v) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\NameCheck.jrag:523
   * @apilevel internal
   */
  public boolean Define_insideLoop(ASTNode _callerNode, ASTNode _childNode) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:224
      return true;
    }
    else {
      return getParent().Define_insideLoop(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java4\\frontend\\NameCheck.jrag:523
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute insideLoop
   */
  protected boolean canDefine_insideLoop(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\GenericMethodsInference.jrag:69
   * @apilevel internal
   */
  public TypeDecl Define_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\GenericMethodsInference.jrag:75
      return iterableTypeAccess().type();
    }
    else {
      return getParent().Define_assignConvertedType(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\GenericMethodsInference.jrag:69
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute assignConvertedType
   */
  protected boolean canDefine_assignConvertedType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:1384
   * @apilevel internal
   */
  public FieldDecl Define_fieldDecl(ASTNode _callerNode, ASTNode _childNode) {
    if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:1390
      return null;
    }
    else {
      return getParent().Define_fieldDecl(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:1384
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute fieldDecl
   */
  protected boolean canDefine_fieldDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:1643
   * @apilevel internal
   */
  public FieldDeclarator Define_erasedField(ASTNode _callerNode, ASTNode _childNode) {
    if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:1654
      {
          throw new Error("FieldDeclarator child of EnhancedForStmt");
        }
    }
    else {
      return getParent().Define_erasedField(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\Generics.jrag:1643
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute erasedField
   */
  protected boolean canDefine_erasedField(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\EffectivelyFinal.jrag:30
   * @apilevel internal
   */
  public boolean Define_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    if (getStmtNoTransform() != null && _callerNode == getStmt()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\EffectivelyFinal.jrag:49
      return false;
    }
    else if (getVariableDeclNoTransform() != null && _callerNode == getVariableDecl()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\EffectivelyFinal.jrag:48
      return modifiedInScope(var);
    }
    else {
      return getParent().Define_inhModifiedInScope(this, _callerNode, var);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\EffectivelyFinal.jrag:30
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inhModifiedInScope
   */
  protected boolean canDefine_inhModifiedInScope(ASTNode _callerNode, ASTNode _childNode, Variable var) {
    return true;
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\TargetType.jrag:31
   * @apilevel internal
   */
  public TypeDecl Define_targetType(ASTNode _callerNode, ASTNode _childNode) {
    if (getExprNoTransform() != null && _callerNode == getExpr()) {
      // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\TargetType.jrag:43
      return iterableTypeAccess().type();
    }
    else {
      return getParent().Define_targetType(this, _callerNode);
    }
  }
  /**
   * @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java8\\frontend\\TargetType.jrag:31
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute targetType
   */
  protected boolean canDefine_targetType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
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
    // @declaredat C:\\Users\\Momoche\\Desktop\\projetStage\\puck2-master-new\\extendj\\java5\\frontend\\EnhancedFor.jrag:43
    {
      java.util.Set<ASTNode> contributors = _map.get(_root);
      if (contributors == null) {
        contributors = new java.util.LinkedHashSet<ASTNode>();
        _map.put((ASTNode) _root, contributors);
      }
      contributors.add(this);
    }
    super.collect_contributors_CompilationUnit_problems(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_CompilationUnit_problems(LinkedList<Problem> collection) {
    super.contributeTo_CompilationUnit_problems(collection);
    for (Problem value : typeProblems()) {
      collection.add(value);
    }
  }
}
